import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import ru.netology.data.*
import ru.netology.exceptions.*
import ru.netology.services.*

class CrudServiceTest {

 @Before
 fun clearBeforeTest() {
  NoteService.clear()
 }

 @Test
 fun createNoteAdd() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  assertEquals(Note(1, "Check", "We'll see"), noteService.getById(1))
 }

 @Test
 fun createCommentAdd() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  assertEquals(Comment(1, "First comment", 1, ), noteService.comments.getById(1))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun createIncorrectCommentAdd() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 2))
 }

 @Test
 fun deleteNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  assertTrue(noteService.delete(1))
 }

 @Test
 fun deleteComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  assertTrue(noteService.comments.delete(1))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun deleteNoteAndComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.delete(1)
  noteService.comments.getById(1)
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectDeleteNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.delete(5)
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectDeleteComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.comments.delete(5)
 }

 @Test
 fun readNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.add(note.copy(3, "Check 2.0", "Result"))
  assertEquals(
   arrayListOf(
    Note(1, "Check", "We'll see"),
    Note(2, "Check 2.0", "Result")
   ),
   noteService.read(1, 2)
  )
 }

 @Test(expected = IdRangeOutOfBoundsException::class)
 fun incorrectReadNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.add(note.copy(3, "Check 2.0", "Result"))
  assertEquals(
   arrayListOf(
    Note(1, "Check", "We'll see"),
    Note(2, "Check 2.0", "Result")
   ),
   noteService.read(1, 3)
  )
 }

 @Test
 fun readCommentByNoteId() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.comments.add(Comment(2, "Lol", 1))
  assertEquals(
   arrayListOf(
    Comment(1, "First comment", 1),
    Comment(2, "Lol", 1)
   ),
   noteService.comments.readByNoteId(1)
  )
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectReadCommentByNoteId() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(5, "First comment", 1))
  noteService.comments.add(Comment(3, "Lol", 1))
  assertEquals(
   arrayListOf(
    Comment(1, "First comment", 1),
    Comment(2, "Lol", 1)
   ),
   noteService.comments.readByNoteId(2)
  )
 }

 @Test
 fun getByIdNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  assertEquals(Note(1, "Check", "We'll see"), noteService.getById(1))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectGetByIdNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  assertEquals(Note(1, "Check", "We'll see"), noteService.getById(2))
 }

 @Test
 fun editNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  assertTrue(noteService.edit(Note(1, "Fix check", "We'll see!")))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectEditNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.edit(Note(2, "Fix check", "We'll see!"))
 }

 @Test
 fun editComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  assertTrue(noteService.comments.edit(Comment(1, "My first comment!", 1)))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectEditComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.comments.edit(Comment(1, "My first comment!", 2))
 }

 @Test
 fun restoreComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.comments.delete(1)
  assertTrue(noteService.comments.restore(1))
 }

 @Test(expected = IdOutOfBoundsException::class)
 fun incorrectRestoreComment() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.comments.add(Comment(1, "First comment", 1))
  noteService.comments.delete(1)
  noteService.comments.restore(2)
 }

 @Test(expected = UnusedFunctionException::class)
 fun restoreNote() {
  val noteService = NoteService
  val note = Note(2, "Check", "We'll see")
  noteService.add(note)
  noteService.delete(1)
  noteService.restore(1)
 }
}