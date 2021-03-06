package com.codepath.photospot.services;

import com.codepath.photospot.daos.PhotoDao;
import com.codepath.photospot.models.Photo;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.SaveCallback;

import java.util.HashMap;

/**
 * End points:
 *
 * Get pix filtered by:
 * - url
 * - location longitude \
 * - location latitude   >  All 3 must be requested together
 * - location radius    /
 * - type
 * - minPixels, maxPixels
 * - minColors, maxColors
 * - category
 * - minLikes maxLikes
 * - minDislikes maxDislikes
 * - createdBy
 * - source
 * - minTime, maxTime
 * - maxResults
 * - skipCount
 * - orderBy:
 *   - Likes (Likes-Dislikes)
 *   - Time  (created_time)
 *
 * Post pic:
 * - Photo object
 *
 * Update pic:
 * - Photo object
 *
 * Delete pic:
 * - Photo object
 *
 * Like a pic:
 * - Photo object
 *
 * Dislike a pic:
 * - Photo object
 *
 * @author dfriedman
 *
 */
public interface ParseService {

    public void getPhotos(HashMap<String, Object> params, FindCallback<PhotoDao> callback);

    public void postPhoto(Photo photo, SaveCallback callback);

    public void updatePhoto(Photo photo, SaveCallback callback);

    public void deletePhoto(Photo photo, DeleteCallback callback);

    public void likePhoto(Photo photo);

    public void dislikePhoto(Photo photo);
    
}
