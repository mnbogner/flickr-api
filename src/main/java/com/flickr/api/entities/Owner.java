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
package com.flickr.api.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public class Owner implements BaseUser {

    private final String id;
    private final String username;
    private final String realname;
    private final String location;
    private final Avatar avatar;

    Owner(JSONObject json) throws JSONException {
        id = json.getString("nsid");
        username = json.getString("username");
        realname = json.getString("realname");
        location = json.getString("location");
        avatar = new Avatar(json, id);
    }

    /**
     * Get the user avatar
     *
     * @return The avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * Get the user location
     *
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    @Override
    public String getRealName() {
        return realname;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return realname;
    }
}
