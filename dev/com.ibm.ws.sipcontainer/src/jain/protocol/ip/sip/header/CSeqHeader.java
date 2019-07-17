/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package jain.protocol.ip.sip.header;

import jain.protocol.ip.sip.SipParseException;

/**
 * <p>
 * This interface represents the CSeq (command sequence) general-header.
 * Clients must add the CSeqHeader to every Request.
 * A CSeqHeader in a Request contains the Request
 * method and a single decimal sequence number chosen by the requesting
 * client, unique within a single value of Call-ID. The sequence number
 * must be expressible as a 32-bit unsigned integer. The initial value
 * of the sequence number is arbitrary, but must be less than 2**31.
 * Consecutive Requests that differ in method, headers or body,
 * but have the same CallIdHeader must contain strictly monotonically
 * increasing and contiguous sequence numbers; sequence numbers do not
 * wrap around.  Retransmissions of the same Request carry the same
 * sequence number, but an INVITE Request with a different message body or
 * different headers (a "re-invitation") acquires a new, higher
 * sequence number. A server must echo the CSeqHeader from the Request
 * in its Response. If the method value is missing in the received
 * CSeqHeader, the server fills it in appropriately.
 * </p><p>
 * ACK and CANCEL Requests must contain the same CSeqHeader sequence number (but not method)
 * as the INVITE Request they refer to, while a BYE Request cancelling an
 * invitation must have a higher sequence number. A BYE Request with a
 * CSeqHeader sequence number that is not higher should cause a BAD_REQUEST
 * Response to be generated.
 * </p><p>
 * A user agent server must remember the highest sequence number for any
 * INVITE Request with the same CallIdHeader. The server must respond
 * to, and then discard, any INVITE Request with a lower sequence
 * number.
 * </p><p>
 * All Requests spawned in a parallel search have the same CSeqHeader as
 * the Request triggering the parallel search.
 * </p><p>
 * The method value allows the client to distinguish the Response to an
 * INVITE Request from that of a response to a CANCEL Request.
 * CANCEL Requests can be generated by proxies; if they were to increase the
 * sequence number, it might conflict with a later Request issued by
 * the user agent for the same call.
 * </p><p>
 * Forked Requests must have the same CSeqHeader as there would be ambiguity
 * otherwise between these forked Requests and later BYE Requests issued by the
 * client user agent.
 * </p>
 *
 * @version 1.0
 *
 */
public interface CSeqHeader extends Header
{
    
    /**
     * Set method of CSeqHeader
     * @param <var>method</var> method
     * @throws IllegalArgumentException if method is null
     * @throws SipParseException if method is not accepted by implementation
     */
    public void setMethod(String method)
                 throws IllegalArgumentException,SipParseException;
    
    /**
     * Gets sequence number of CSeqHeader
     * @return sequence number of CSeqHeader
     */
    public long getSequenceNumber();
    
    /**
     * Gets method of CSeqHeader
     * @return method of CSeqHeader
     */
    public String getMethod();
    
    /**
     * Set sequence number of CSeqHeader
     * @param <var>sequenceNumber</var> sequence number
     * @throws SipParseException if sequenceNumber is not accepted by implementation
     */
    public void setSequenceNumber(long sequenceNumber)
                 throws SipParseException;
    
    //////////////////////////////////////////////////////////////
    
    /**
     * Name of CSeqHeader
     */
    public final static String name = "CSeq";
}
