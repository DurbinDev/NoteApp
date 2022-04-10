package com.example.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(6.dp)) {

        TopAppBar(
            title = {
                    Text(stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "notifications icon")
            },
            backgroundColor = Color(0x75817FC2))
        
        //Content
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            
            NoteInputText(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 8.dp),
                text = title,
                Label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                            title = it
                })

            NoteInputText(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp),
                text = description,
                Label = "Add Note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                        description = it
                })
            
            NoteButton(
                text = "Save",
                onCLick = {
                    if(title.isNotEmpty() && description.isNotEmpty()){
                        onAddNote(Note(title = title, description = description))

                        title = ""
                        description = ""

                        Toast.makeText(
                            context, "Note Added",
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn(){
            items(notes){ note->
                NoteRow(
                    note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    })
            }
        }
    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {
        Surface(
            modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, bottomStart = 20.dp))
                .fillMaxWidth(),
        color = Color(0x75817FC2),
        elevation = 6.dp) {
            Column(
                modifier = Modifier
                    .clickable {
                        onNoteClicked(note)
                    }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start) {

                Text(
                    note.title,
                    style = MaterialTheme.typography.subtitle2)
                Text(
                    note.description,
                    style = MaterialTheme.typography.subtitle1)
                Text(note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                    style = MaterialTheme.typography.caption)
            }
        }
    }


























@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NoteScreen(
        notes = emptyList(),
        onAddNote = {},
        onRemoveNote = {})
}




















