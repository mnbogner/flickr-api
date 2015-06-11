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

import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Comment;
import com.flickr.api.entities.CommentResponse;
import com.flickr.api.entities.CommentsResponse;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PhotosResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.Photoset;
import com.flickr.api.entities.PhotosetInfos;
import com.flickr.api.entities.PhotosetInfosResponse;
import com.flickr.api.entities.PhotosetResponse;
import com.flickr.api.entities.PhotosetsResponse;
import com.flickr.api.entities.VoidResponse;
import java.util.List;

/**
 *
 * @author Fabien Barbero
 */
public class PhotosetsService extends FlickrService {

    PhotosetsService(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    /**
     * Returns the photosets belonging to the specified user.
     *
     * @param user The user to get a photoset list for
     * @param perPage The number of sets to get per page. The maximum number of sets per page is 500.
     * @param page The page of results to get
     * @return The sets
     * @throws FlickrException Error getting the sets
     */
    public Paginated<Photoset> getPhotosets(BaseUser user, int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.getList");
        args.addParam("user_id", user.getId());
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        return doGet(args, PhotosetsResponse.class).getPaginated();
    }

    /**
     * Get the list of photos in a set.
     *
     * @param photoset The photoset
     * @param perPage Number of photos to return per page. The maximum allowed value is 500.
     * @param page The page of results to return
     * @return The photos
     * @throws FlickrException Error getting the photos
     */
    public Paginated<Photo> getPhotos(Photoset photoset, int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.getPhotos");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        return doGet(args, PhotosResponse.class).getPaginated();
    }

    /**
     * Gets information about a photoset.
     *
     * @param photoset The photoset
     * @return The photoset
     * @throws FlickrException Error getting the set
     */
    public PhotosetInfos getInfos(Photoset photoset) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.getInfo");
        args.addParam("photoset_id", photoset.getId());
        return doGet(args, PhotosetInfosResponse.class).getInfos();
    }

    /**
     * Returns the comments for a photoset.
     *
     * @param photoset The photoset
     * @return The comments
     * @throws FlickrException Error getting the comments
     */
    public List<Comment> getComments(Photoset photoset) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.comments.getList");
        args.addParam("photoset_id", photoset.getId());
        return doGet(args, CommentsResponse.class).getList();
    }

    /**
     * Create a new photoset
     *
     * @param title The photoset title
     * @param description The photoset description
     * @param primaryPhoto The photoset priùary photo
     * @return The new photoset
     * @throws FlickrException Error creating the photoset
     */
    public Photoset createPhotoset(String title, String description, Photo primaryPhoto) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.create");
        args.addParam("title", title);
        args.addParam("description", description);
        args.addParam("primary_photo_id", primaryPhoto.getId());
        return doPost(args, PhotosetResponse.class).getPhotoset();
    }
    
    // there is no reason to require a Photo object if only the id is used
    public Photoset createPhotoset(String title, String description, String primaryPhotoId) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.create");
        args.addParam("title", title);
        args.addParam("description", description);
        args.addParam("primary_photo_id", primaryPhotoId);
        return doPost(args, PhotosetResponse.class).getPhotoset();
    }

    /**
     * Delete a photoset
     *
     * @param photoset The photoset to delete
     * @throws FlickrException Error deleting the photoset
     */
    public void deletePhotoset(Photoset photoset) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.delete");
        args.addParam("photoset_id", photoset.getId());
        doPost(args, VoidResponse.class);
    }

    /**
     * Modify the meta-data for a photoset.
     *
     * @param photoset The photoset to modify
     * @param title The new title
     * @param description The new description
     * @throws FlickrException Error editing the meta
     */
    public void editPhotosetMeta(Photoset photoset, String title, String description) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.editMeta");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("title", title);
        args.addParam("description", description);
        doPost(args, VoidResponse.class);
    }

    /**
     * Set photoset primary photo
     *
     * @param photoset The photoset
     * @param primaryPhoto The new primary photo
     * @throws FlickrException Error setting the primary photo
     */
    public void setPrimaryPhoto(Photoset photoset, Photo primaryPhoto) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.setPrimaryPhoto");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("photo_id", primaryPhoto.getId());
        doPost(args, VoidResponse.class);
    }

    /**
     * Add a photo to a set
     *
     * @param photoset The set
     * @param photo The photo to add
     * @throws FlickrException Error adding the photo
     */
    public void addPhotoToSet(Photoset photoset, Photo photo) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.addPhoto");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("photo_id", photo.getId());
        doPost(args, VoidResponse.class);
    }
    
    // there is no reason to require a Photo object if only the id is used
    public void addPhotoToSet(Photoset photoset, String photoId) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.addPhoto");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("photo_id", photoId);
        doPost(args, VoidResponse.class);
    }

    /**
     * Remove multiple photos from a photoset.
     *
     * @param photoset The photoset
     * @param photos The photos to remove
     * @throws FlickrException Error removing the photos
     */
    public void removePhotosFromSet(Photoset photoset, Photo... photos) throws FlickrException {
        StringBuilder builder = new StringBuilder();
        for (Photo photo : photos) {
            builder.append(photo.getId()).append(",");
        }

        CommandArguments args = new CommandArguments("flickr.photosets.removePhotos");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("photo_ids", builder);
        doPost(args, VoidResponse.class);
    }

    /**
     * Add a comment to the photoset
     *
     * @param photoset The photoset
     * @param text The comment text
     * @return The created comment
     * @throws FlickrException Error adding the comment
     */
    public Comment addComment(Photoset photoset, String text) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.comments.addComment");
        args.addParam("photoset_id", photoset.getId());
        args.addParam("comment_text", text);
        return doPost(args, CommentResponse.class).getComment();
    }

    /**
     * Delete a comment on a photoset
     *
     * @param comment The comment to delete
     * @throws FlickrException Error deleting the comment
     */
    public void deleteComment(Comment comment) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.comments.deleteComment");
        args.addParam("comment_id", comment.getId());
        doPost(args, VoidResponse.class);
    }

    /**
     * Edit the text of a comment as the currently authenticated user.
     *
     * @param comment The comment to edit
     * @param text The new comment text
     * @throws FlickrException Error editing the comment
     */
    public void editComment(Comment comment, String text) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.photosets.comments.editComment");
        args.addParam("comment_id", comment.getId());
        args.addParam("comment_text", text);
        doPost(args, VoidResponse.class);
    }
}