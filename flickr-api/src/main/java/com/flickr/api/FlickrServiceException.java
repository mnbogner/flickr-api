/*
 * Copyright (C) 2011 by Fabien Barbero
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flickr.api;

/**
 *
 * @author Fabien Barbero
 */
public class FlickrServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private final FlickrErrorCode errorCode;

    public FlickrServiceException(String s, Throwable t, FlickrErrorCode errorCode) {
        super(s, t);
        this.errorCode = errorCode;
    }

    public FlickrServiceException(String s, Throwable t) {
        super(s, t);
        errorCode = null;
    }

    public FlickrServiceException(String s, FlickrErrorCode errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public FlickrServiceException(String s) {
        super(s);
        errorCode = null;
    }

    /**
     * Get the error code
     *
     * @return The error code or null
     */
    public final FlickrErrorCode getErrorCode() {
        return errorCode;
    }

}
