/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ws.security.components.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Properties;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.util.Loader;

/**
 * CryptoFactory.
 * This class serves as a way to to mitigate the migration from WSS4J 1.6 to 2.0 as required by
 * the migration of CXF from 2.6.2 to 3.4. We modify it to return the new 2.0 version the Crpto class
 * which is now an implementation of the 1.6 Crypto class. Meaning that users running an application
 * with a custom callback handler based off 1.6 can continue running their
 * applications and have the updated code. 
 */
public class CryptoFactory /*extends org.apache.wss4j.common.crypto.CryptoFactory*/ {
    private static final org.slf4j.Logger LOG =
        org.slf4j.LoggerFactory.getLogger(CryptoFactory.class);

    /**
     * getInstance
     * <p/>
     * Returns an instance of Crypto. This method uses the file
     * <code>crypto.properties</code> to determine which implementation to
     * use. Thus the property <code>org.apache.wss4j.crypto.provider</code>
     * must define the classname of the Crypto implementation. The file
     * may contain other property definitions as well. These properties are
     * handed over to the Crypto implementation. The file
     * <code>crypto.properties</code> is loaded with the
     * <code>Loader.getResource()</code> method.
     * <p/>
     *
     * @return The crypto implementation was defined
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    public Crypto getInstance() throws WSSecurityException {
        // Liberty Change returns 2.0 impl
        return org.apache.wss4j.common.crypto.CryptoFactory.getInstance();//getInstance("crypto.properties");
    }

    /**
     * getInstance
     * <p/>
     * Returns an instance of Crypto. The properties are handed over the the crypto
     * implementation. The properties must at least contain the Crypto implementation
     * class name as the value of the property : org.apache.wss4j.crypto.provider
     * <p/>
     *
     * @param properties      The Properties that are forwarded to the crypto implementation
     *                        and the Crypto impl class name.
     *                        These properties are dependent on the crypto implementation
     * @return The cyrpto implementation or null if no cryptoClassName was defined
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    public static Crypto getInstance(Properties properties) throws WSSecurityException {
        if (properties == null) {
            LOG.debug("Cannot load Crypto instance as properties object is null");
            throw new WSSecurityException(WSSecurityException.ErrorCode.FAILURE,
                    "empty", new Object[] {"Cannot load Crypto instance as properties object is null"});
        }
        // Liberty Change returns 2.0 impl
        return org.apache.wss4j.common.crypto.CryptoFactory.getInstance(properties);
    }

    

    /**
     * getInstance
     * <p/>
     * Returns an instance of Crypto. The supplied map is handed over the the crypto
     * implementation. The map can be <code>null</code>. It is dependent on the
     * Crypto implementation how the initialization is done in this case.
     * <p/>
     *
     * @param cryptoClass     This is the crypto implementation class. No default is
     *                        provided here.
     * @param map             The Maps that is forwarded to the crypto implementation.
     *                        These contents of the map are dependent on the
     *                        underlying crypto implementation specified in the
     *                        cryptoClassName parameter.
     * @return The crypto implementation or null if no cryptoClassName was defined
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    public static Crypto getInstance(
        Class<? extends Crypto> cryptoClass,
        Map<Object, Object> map
    ) throws WSSecurityException {
        return loadClass(cryptoClass, map, Loader.getClassLoader(CryptoFactory.class));
        //return org.apache.wss4j.common.crypto.CryptoFactory.getInstance(cryptoClass, map);
    
    }

    /**
     * getInstance
     * <p/>
     * Returns an instance of Crypto. This method uses the specified filename
     * to load a property file. This file shall use the property
     * <code>org.apache.wss4j.crypto.provider</code>
     * to define the classname of the Crypto implementation. The file
     * may contain other property definitions as well. These properties are
     * handed over to the Crypto implementation. The specified file
     * is loaded with the <code>Loader.getResource()</code> method.
     * <p/>
     *
     * @param propFilename The name of the property file to load
     * @return The crypto implementation that was defined
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    public static Crypto getInstance(String propFilename) throws WSSecurityException {
        // Liberty Change returns 2.0 impl
        return org.apache.wss4j.common.crypto.CryptoFactory.getInstance(propFilename, Loader.getClassLoader(CryptoFactory.class));
    }

    public static Crypto getInstance(
        String propFilename,
        ClassLoader customClassLoader
    ) throws WSSecurityException {
        Properties properties = getProperties(propFilename, customClassLoader);
        // Liberty Change returns 2.0 impl
        return org.apache.wss4j.common.crypto.CryptoFactory.getInstance(properties, customClassLoader, null);
    }

    /**
     * This allows loading the classes with a custom class loader
     * @param cryptoClass
     * @param map
     * @param loader
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    private static Crypto loadClass(
        Class<? extends Crypto> cryptoClass,
        Map<Object, Object> map,
        ClassLoader loader
    ) throws WSSecurityException {
        LOG.debug("Using Crypto Engine [{}]", cryptoClass);
        try {
            Constructor<? extends Crypto> c = null;
            try {
                Class<?>[] classes = new Class[]{Map.class, ClassLoader.class};
                c = cryptoClass.getConstructor(classes);
                return c.newInstance(map, loader);
            } catch (NoSuchMethodException ex) {

            }
        } catch (java.lang.Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to instantiate: " + cryptoClass.getName(), e);
            }
            throw new WSSecurityException(WSSecurityException.ErrorCode.FAILURE, e,
                    "empty", new Object[] {cryptoClass + " cannot create instance"});
        }
        return null;
    }

    /**
     * This allows loading the classes with a custom class loader
     * @param cryptoClass
     * @param map
     * @param loader
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    private static Crypto loadClass(
        Class<? extends Crypto> cryptoClass,
        Properties map,
        ClassLoader loader
    ) throws WSSecurityException {
        LOG.debug("Using Crypto Engine [{}]", cryptoClass);
        try {
            Constructor<? extends Crypto> c = null;
            try {
                Class<?>[] classes = new Class[]{Properties.class, ClassLoader.class};
                c = cryptoClass.getConstructor(classes);
                return c.newInstance(map, loader);
            } catch (NoSuchMethodException ex) {

            }
        } catch (java.lang.Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to instantiate: " + cryptoClass.getName(), e);
            }
            throw new WSSecurityException(WSSecurityException.ErrorCode.FAILURE, e,
                    "empty", new Object[] {cryptoClass + " cannot create instance"});
        }
        return null;
    }

    /**
     * This allows loading the resources with a custom class loader
     * @param propFilename
     * @param loader
     * @return a Properties object loaded from the propFilename argument
     * @throws WSSecurityException if there is an error in loading the crypto properties
     */
    public static Properties getProperties(
        String propFilename,
        ClassLoader loader
    ) throws WSSecurityException {
        Properties properties = new Properties();
        try {
            InputStream is = Loader.loadInputStream(loader, propFilename);
            if (is == null) {
                throw new WSSecurityException(
                    WSSecurityException.ErrorCode.FAILURE,
                    "resourceNotFound",
                    new Object[] {propFilename}
                );
            }
            properties.load(is);
            is.close();
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Cannot find resource: " + propFilename, e);
            }
            throw new WSSecurityException(
                WSSecurityException.ErrorCode.FAILURE, e,
                "resourceNotFound", new Object[] {propFilename}
            );
        }
        return properties;
    }

}

