/*******************************************************************************
 * Copyright (c) 2017, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.example2;

import static componenttest.annotation.SkipForRepeat.EE10_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE10_OR_LATER_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE11_OR_LATER_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE8_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE8_OR_LATER_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE9_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE9_OR_LATER_FEATURES;
import static componenttest.annotation.SkipForRepeat.NO_MODIFICATION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;
import com.ibm.websphere.simplicity.config.KeyStore;
import com.ibm.websphere.simplicity.config.SSL;
import com.ibm.websphere.simplicity.config.ServerConfiguration;
import com.ibm.websphere.simplicity.log.Log;

import app2.web.TestServletB;
import componenttest.annotation.Server;
import componenttest.annotation.SkipForRepeat;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;

/**
 * Example Shrinkwrap FAT project:
 * <li> Application packaging is done in the @BeforeClass, instead of ant scripting.
 * <li> Injects servers via @Server annotation. Annotation value corresponds to the
 * server directory name in 'publish/servers/%annotation_value%' where ports get
 * assigned to the LibertyServer instance when the 'testports.properties' does not
 * get used.
 * <li> Specifies an @RunWith(FATRunner.class) annotation. Traditionally this has been
 * added to bytecode automatically by ant.
 * <li> Uses the @TestServlet annotation to define test servlets. Notice that not all @Test
 * methods are defined in this class. All of the @Test methods are defined on the test
 * servlet referenced by the annotation, and will be run whenever this test class runs.
 */
@RunWith(FATRunner.class)
public class MyTest extends FATServletClient {

    public static final String APP_NAME = "app2";
    private final String KEYSTORE_DEFAULT_LOCATION = "${server.output.dir}/resources/security/key.p12";
    private final String KEYSTORE_SSL_CERT_ERROR = "CWPKI0804E";
    private final String KEYSTORE_LOCATION_NOT_LOADED = "CWPKI0033E";

    private static Class<?> logClass = MyTest.class;

    @Server("MyFATServer")
    @TestServlet(servlet = TestServletB.class, contextRoot = APP_NAME)
    public static LibertyServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        // Create a WebArchive that will have the file name 'app2.war' once it's written to a file
        // Include the 'app2.web' package and all of it's java classes and sub-packages
        // Automatically includes resources under 'test-applications/APP_NAME/resources/' folder
        // Exports the resulting application to the ${server.config.dir}/apps/ directory
        ShrinkHelper.defaultApp(server, APP_NAME, "app2.web");

        server.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    public void verifyArtifactoryDependency() throws Exception {
        // Confirm that the example Artifactory dependency was download and is available on the classpath
        org.apache.commons.logging.Log.class.getName();
    }

    /**
     * Check that the existing configuration has a keystore element
     *
     * @throws Exception
     */
    @Test
    public void testServerConfig_KeystorePreDefined() throws Exception {
        assertFalse(server.getServerConfiguration().getKeyStores().isEmpty());
    }

    /**
     * Check that removing any existing keystore entries, there is no keystore element on the configuration
     *
     * @throws Exception
     */
    @Test
    public void testServerConfig_RemoveKeystores() throws Exception {
        clearKeystoresAndRefresh();
        assertTrue(server.getServerConfiguration().getKeyStores().isEmpty());
    }

    /**
     * Check the addition of a new keystore using the default location and validate that location is properly set
     *
     * @throws Exception
     */
    @Test
    public void testServerConfig_AddDefaultKeystore() throws Exception {
        addKeystore(KEYSTORE_DEFAULT_LOCATION);
        //server.validateAppLoaded(APP_NAME);

        KeyStore keystore = server.getServerConfiguration().getKeyStores().stream().findFirst().get();

        assertFalse(server.getServerConfiguration().getKeyStores().isEmpty());
        assertEquals(KEYSTORE_DEFAULT_LOCATION, keystore.getLocation());
    }

    /**
     * Check that a URL as the location of keystore results to one of the following errors in the output.txt:
     * CWPKI0804E: SSL certificate creation error. {0}
     * CWPKI0033E: The keystore located at {0} did not load because of the following error: {1}
     *
     * @throws Exception
     */
    @Test
    public void testServerConfig_URLKeystoreLocation() throws Exception {
        addKeystore("http://domain.tld/keystore/key.p12");

        KeyStore keystore = server.getServerConfiguration().getKeyStores().stream().findFirst().get();

        Log.info(logClass, "testServerConfig_URLKeystoreLocation", "-----------Server config path : " + server.getServerConfigurationPath());

        //FIXME: Searching on the log file (which one?) for a string is not working
        //assertNotNull("Log entry not found", server.waitForStringInLog("-----------Server"));

        assertFalse(server.getServerConfiguration().getKeyStores().isEmpty());
        assertEquals("http://domain.tld/keystore/key.p12", keystore.getLocation());

    }

    /**
     * This method gets the existing server configuration and removes any keystore references.
     *
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static void clearKeystoresAndRefresh() throws Exception {
        // Delete the keystores entries and make sure they are unregistered
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.getKeyStores().clear();

        server.updateServerConfiguration(serverConfig);
        //server.saveServerConfiguration();

    }

    /**
     * Update the server configuration by adding a new keystore element passed as parameter
     *
     * @param keystore
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static void addKeystore(KeyStore keystore) throws Exception {
        // Delete the keystores entries and make sure they are unregistered
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.getKeyStores().clear();

        // Add the a new keystore
        serverConfig.getKeyStores().add(keystore);
        server.updateServerConfiguration(serverConfig);
        //server.saveServerConfiguration();

    }

    /**
     * Update the server configuration by adding a new keystore element, removing any existing ones first.
     * Password is defaulted to a dummy one and location is passed as parameter.
     *
     * @param location
     * @throws Exception
     */
    private static void addKeystore(String location) throws Exception {
        // Delete the keystores entries and make sure they are unregistered
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.getKeyStores().clear();

        // Add the a new keystore
        KeyStore keystore = new KeyStore();
        keystore.setId("defKeystoreID");
        keystore.setLocation(location);
        keystore.setPassword("password");
        serverConfig.getKeyStores().add(keystore);
        server.updateServerConfiguration(serverConfig);
        //server.saveServerConfiguration();

    }

    /**
     * Update the server configuration by adding a new SSL element.
     *
     * @param id
     * @param keyStoreRef
     * @param trustStoreRef
     * @throws Exception
     */
    public static void addSSL(String id, String keyStoreRef, String trustStoreRef) throws Exception {
        ServerConfiguration config = server.getServerConfiguration();
        SSL ssl = new SSL();
        ssl.setId(id);
        ssl.setKeyStoreRef(keyStoreRef);
        ssl.setTrustStoreRef(trustStoreRef);
        config.addSSL(ssl);
        server.updateServerConfiguration(config);
    }

    @Test
    @SkipForRepeat({ EE8_OR_LATER_FEATURES })
    public void testEE7Only() throws Exception {
        // This test will only run for the EE7 feature iteration (i.e. NO_MODIFICATION)

        // Verify only EE7 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 7 feature 'servlet-3.1' to be enabled but was not: " + features,
                   features.contains("servlet-3.1"));
        assertTrue("No EE8 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-4.0"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE9_OR_LATER_FEATURES })
    public void testEE8Only() throws Exception {
        // This test will only run for the EE 8 iteration

        // Verify only EE8 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 8 feature 'servlet-4.0' to be enabled but was not: " + features,
                   features.contains("servlet-4.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE8_FEATURES, EE10_OR_LATER_FEATURES })
    public void testEE9Only() throws Exception {
        // This test will only run for the EE9 iteration

        // Verify only EE9 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 9 feature 'servlet-5.0' to be enabled but was not: " + features,
                   features.contains("servlet-5.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE8_FEATURES, EE9_FEATURES, EE11_OR_LATER_FEATURES })
    public void testEE10Only() throws Exception {
        // This test will only run for the EE10 iteration

        // Verify only EE10 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 10 feature 'servlet-6.0' to be enabled but was not: " + features,
                   features.contains("servlet-6.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE8_FEATURES, EE9_FEATURES, EE10_FEATURES })
    public void testEE11Only() throws Exception {
        // This test will only run for the EE11 iteration

        // Verify only EE11 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 11 feature 'servlet-6.1' to be enabled but was not: " + features,
                   features.contains("servlet-6.1"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }
}
