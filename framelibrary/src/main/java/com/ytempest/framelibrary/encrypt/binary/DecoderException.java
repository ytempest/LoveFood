/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ytempest.framelibrary.encrypt.binary;


/**
 * Thrown when there is a failure condition during the decoding process. This exception is thrown when a {@link Decoder}
 * encounters a decoding specific exception such as invalid data, or characters outside of the expected range.
 *
 * @version $Id: DecoderException.java 1619948 2014-08-22 22:53:55Z ggregory $
 */
public class DecoderException extends Exception {

    /**
     * Declares the Serial Version Uid.
     *
     * @see <a href="http://c2.com/cgi/wiki?AlwaysDeclareSerialVersionUid">Always Declare Serial Version Uid</a>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     *
     * @since 1.4
     */
    public DecoderException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently
     * be initialized by a call to {@link #initCause}.
     *
     * @param message
     *            The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public DecoderException(final String message) {
        super(message);
    }


}
