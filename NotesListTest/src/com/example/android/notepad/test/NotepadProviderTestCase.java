package com.example.android.notepad.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

import com.example.android.notepad.NotePad;
import com.example.android.notepad.NotePadProvider;
import com.example.android.notepad.NotePad.Notes;

public class NotepadProviderTestCase extends ProviderTestCase2<NotePadProvider> {

    public NotepadProviderTestCase() {
        super(NotePadProvider.class, "com.google.provider.NotePad");
    }

    private ContentResolver contentResolver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        contentResolver = getMockContentResolver();
    }
    
    public void testShouldCreateAndRetrieveNote() {
        long time = System.currentTimeMillis();
        createNote(time);
        
        Cursor cursor = contentResolver.query(NotePad.Notes.CONTENT_URI, null, null, null, null);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        
        assertEquals("a title", cursor.getString(cursor.getColumnIndex(Notes.TITLE)));
        assertEquals("this is the content of the note.", cursor.getString(cursor.getColumnIndex(Notes.NOTE)));
        assertEquals(time, cursor.getLong(cursor.getColumnIndex(Notes.CREATED_DATE)));
        cursor.close();
    }
    
    public void testShouldModifyNoteContent() {
        Uri createdNoteUri = createNote(System.currentTimeMillis());
        ContentValues values = new ContentValues();
        values.put(Notes.NOTE, "new content");
        contentResolver.update(createdNoteUri, values, null, null);
        
        Cursor cursor = contentResolver.query(NotePad.Notes.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        assertEquals("new content", cursor.getString(cursor.getColumnIndex(Notes.NOTE)));
        cursor.close();
    }
    
    private Uri createNote(long creationTime) {
        ContentValues values = new ContentValues();
        values.put(Notes.CREATED_DATE, creationTime);
        values.put(Notes.TITLE, "a title");
        values.put(Notes.NOTE, "this is the content of the note.");
        return contentResolver.insert(Notes.CONTENT_URI, values);
    }

}
