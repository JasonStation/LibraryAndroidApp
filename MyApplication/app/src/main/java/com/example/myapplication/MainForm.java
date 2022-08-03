package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainForm extends AppCompatActivity {
    public static ArrayList<Integer> bookId = new ArrayList<Integer>();
    public static ArrayList<Integer> bookCover = new ArrayList<Integer>();
    public static ArrayList<String> bookTitle = new ArrayList<String>();
    public static ArrayList<String> bookAuthor = new ArrayList<String>();
    public static ArrayList<String> bookSynopsis = new ArrayList<String>();



    public static RecyclerView rView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        TextView accountEmail = (TextView) findViewById(R.id.accountInfo);
        if(MainActivity.loggedAccount != 0)
            accountEmail.setText("Logged in as " + MainActivity.emailAddress.get(MainActivity.userId.indexOf(MainActivity.loggedAccount)));
        rView = findViewById(R.id.recycleViewBooks);
        if(bookId.isEmpty()){
            bookCover.add(R.drawable.harrypotterseries);
            bookCover.add(R.drawable.thehungergameseries);
            bookCover.add(R.drawable.tokillamockingbird);
            bookCover.add(R.drawable.thefaultinourstars);
            bookCover.add(R.drawable.thehobbit);
            bookCover.add(R.drawable.thecatcherintherye);
            bookCover.add(R.drawable.thelordoftherings);
            bookCover.add(R.drawable.fahrenheit451);
            bookCover.add(R.drawable.lookingforalaska);
            bookCover.add(R.drawable.thebookthief);
            bookCover.add(R.drawable.thegiverseries);
            bookCover.add(R.drawable.thehitchhikerguidetothegalaxyseries);
            bookCover.add(R.drawable.theoutsiders);
            bookCover.add(R.drawable.anneofgreengablesseries);
            bookCover.add(R.drawable.hisdarkmaterialsseries);
            bookCover.add(R.drawable.theperksofbeingawallflower);
            bookCover.add(R.drawable.theprincessbride);
            bookCover.add(R.drawable.lordoftheflies);
            bookCover.add(R.drawable.divergentseries);
            bookCover.add(R.drawable.papertowns);
            bookCover.add(R.drawable.themortalinstrumentsseries);
            bookCover.add(R.drawable.anabundanceofkatherines);
            bookCover.add(R.drawable.flowerofalgernon);
            bookCover.add(R.drawable.thirteenreasonswhy);
            bookCover.add(R.drawable.thecuriousincidentofthedog);


            String students_array = "{\"books\":[\n" +
                    "  {\n" +
                    "    \"name\": \"Harry Potter series\",\n" +
                    "    \"author\": \"J.K. Rowling\",\n" +
                    "    \"synopsis\": \"The adventures of Harry Potter, the Boy Who Lived, and his wand-wielding friends at the Hogwarts School of Witchcraft and Wizardry.  Harry, Ron and Hermione must master their craft and battle the machinations of the evil wizard Voldemort and his Death Eaters. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Hunger Games series\",\n" +
                    "    \"author\": \"Suzanne Collins\",\n" +
                    "    \"synopsis\": \"In the ruins of a future North America, a young girl is picked to leave her impoverished district and travel to the decadent Capitol for a battle to the death in the savage Hunger Games. But for Katniss Everdeen, winning the Games only puts her deeper in danger as the strict social order of Panem begins to unravel.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"To Kill a Mockingbird\",\n" +
                    "    \"author\": \"Harper Lee\",\n" +
                    "    \"synopsis\": \"Author Harper Lee explores racial tensions in the fictional \\\"tired old town\\\" of Maycomb, Ala., through the eyes of 6-year-old Scout Finch. As her lawyer father, Atticus, defends a black man accused of rape, Scout and her friends learn about the unjust treatment of African-Americans — and their mysterious neighbor, Boo Radley.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Fault in Our Stars\",\n" +
                    "    \"author\": \"John Green\",\n" +
                    "    \"synopsis\": \"Despite the tumor-shrinking medical miracle that has bought her a few more years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at the Cancer Kid Support Group, Hazel's story is about to be completely rewritten.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Hobbit\",\n" +
                    "    \"author\": \"J.R.R. Tolkien\",\n" +
                    "    \"synopsis\": \"Bilbo Baggins, a respectable, well-to-do hobbit, lives comfortably in his hobbit hole until the day the wandering wizard Gandalf chooses him to take part in an adventure from which he may never return.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Catcher in the Rye\",\n" +
                    "    \"author\": \"J.D. Salinger\",\n" +
                    "    \"synopsis\": \"With the author's death, the classic novel about young Holden Caulfield's disillusionment with the adult world and its \\\"phoniness\\\" will only rise in popularity — and controversy, since it is a favorite target of censors, who often cite profanity and sexual references in their efforts to ban the book.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Lord of the Rings\",\n" +
                    "    \"author\": \"J.R.R. Tolkien\",\n" +
                    "    \"synopsis\": \"Tolkien's seminal three-volume epic chronicles the War of the Ring, in which Frodo the hobbit and his companions set out to destroy the evil Ring of Power and restore peace to Middle-earth. The beloved trilogy still casts a long shadow, having established some of the most familiar and enduring tropes in fantasy literature. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Fahrenheit 451\",\n" +
                    "    \"author\": \"Ray Bradbury\",\n" +
                    "    \"synopsis\": \"In a far future world, television dominates, and books are outlawed. The totalitarian regime has ordered all books to be burned by \\\"firemen,\\\" whose job is to start the fires rather than stop them. But one fireman begins to see the value of the printed word.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Looking for Alaska\",\n" +
                    "    \"author\": \"John Green\",\n" +
                    "    \"synopsis\": \"Sixteen-year-old Miles' first year at Culver Creek Preparatory School in Alabama includes good friends and great pranks, but is defined by the search for answers about life and death after a fatal car crash.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Book Thief\",\n" +
                    "    \"author\": \"Markus Zusak\",\n" +
                    "    \"synopsis\": \"Trying to make sense of the horrors of World War II, Death relates the story of Liesel — a young German girl whose book-stealing and storytelling talents help sustain her family and the Jewish man they are hiding, as well as their neighbors.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Giver series\",\n" +
                    "    \"author\": \"Lois Lowry\",\n" +
                    "    \"synopsis\": \"In the future, society has eliminated discord, converting everyone to \\\"Sameness.\\\" In three linked stories, Jonas, destined to hold memories of the time before Sameness; Kira, an orphan with a twisted leg; and healer Matty must discover the truth about their society and restore emotion, meaning and balance to their world.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Hitchhiker's Guide to the Galaxy series\",\n" +
                    "    \"author\": \"Douglas Adams\",\n" +
                    "    \"synopsis\": \"In this collection of novels, Arthur Dent is introduced to the galaxy at large when he is rescued by an alien friend seconds before Earth's destruction, and embarks on a series of amazing adventures, from the mattress swamps of Sqornshellous Zeta to the Restaurant at the End of the Universe.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Outsiders\",\n" +
                    "    \"author\": \"S.E. Hinton\",\n" +
                    "    \"synopsis\": \"S.E. Hinton was just 16 years old when she wrote this novel about kids getting caught up in class struggles. Ponyboy is a greaser, from the wrong side of the tracks; he runs afoul of the upper-class Socs, leading to an epic rumble between the two gangs.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Anne of Green Gables series\",\n" +
                    "    \"author\": \"L.M. Montgomery\",\n" +
                    "    \"synopsis\": \"In this collection of eight novels by Lucy Maude Montgomery, Matthew and Marilla Cuthbert, a rather prim and elderly brother and sister pair, send away for an orphan boy to help them run their farm on Canada's Prince Edward Island. But when the orphan arrives, he's not a he, he's a she — the loquacious and dreamy red-haired Anne-with-an-E Shirley — who quickly takes up a central place in their hearts. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"His Dark Materials series\",\n" +
                    "    \"author\": \"Philip Pullman\",\n" +
                    "    \"synopsis\": \"In this hit series, young Lyra Belacqua tries to prevent kidnapped children from becoming the subject of gruesome experiments; helps Will Parry — a boy from another world — search for his father; and finds that she and Will are caught in a battle between the angelic forces of the Authority and those gathered by her rebel uncle, Lord Asriel.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Perks of Being a Wallflower\",\n" +
                    "    \"author\": \"Stephen Chbosky\",\n" +
                    "    \"synopsis\": \"In a thought-provoking, coming-of-age novel, teenager Charlie struggles to cope with the complex world of high school. He deals with the confusions of sex and love, the temptations of drugs and the pain of losing a close friend and favorite aunt.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Princess Bride\",\n" +
                    "    \"author\": \"William Goldman\",\n" +
                    "    \"synopsis\": \"This tale of a handsome farm boy who, aided by a drunken swordsman and a gentle giant, rescues a beautiful princess named Buttercup comes with a slyly humorous, metafictional edge=> Goldman claims to have merely abridged an earlier text by one \\\"S. Morgenstern\\\" (actually a pseudonym) and peppers his text with clever commentary. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Lord of the Flies\",\n" +
                    "    \"author\": \"William Golding\",\n" +
                    "    \"synopsis\": \"The classic study of human nature depicts the degeneration of a group of schoolboys marooned on a desert island. Ralph, Piggy, Simon and their fellow castaways attempt to develop their own society — and fail disastrously.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Divergent series\",\n" +
                    "    \"author\": \"Veronica Roth\",\n" +
                    "    \"synopsis\": \"In a future Chicago, 16-year-old Beatrice Prior must choose among five predetermined factions to define her identity for the rest of her life, a decision made more difficult when she discovers that she is an anomaly who does not fit into any one group, and that the society she lives in is not perfect after all.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Paper Towns\",\n" +
                    "    \"author\": \"John Green\",\n" +
                    "    \"synopsis\": \"One month before graduating from his Central Florida high school, Quentin \\\"Q\\\" Jacobsen basks in the predictable boringness of his life, until the beautiful and exciting Margo Roth Spiegelman, Q's neighbor and classmate, takes him on a midnight adventure and then mysteriously disappears. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Mortal Instruments series\",\n" +
                    "    \"author\": \"Cassandra Clare\",\n" +
                    "    \"synopsis\": \"Able to see demons and those who hunt them, Clary Fray is drawn into the world of the Shadowhunters when her mother slips into a coma and travels to the City of Glass, the capital of their secretive country, where she uncovers important truths about her family's past. \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"An Abundance of Katherines\",\n" +
                    "    \"author\": \"John Green\",\n" +
                    "    \"synopsis\": \"Always being dumped by girls named Katherine, Colin Singleton, a washed-up child prodigy with a Judge Judy-obsessed best friend, embarks on a quest to prove The Theorem of Underlying Katherine Predictability, which will affect all of his future relationships and change his life.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Flowers For Algernon\",\n" +
                    "    \"author\": \"Daniel Keyes\",\n" +
                    "    \"synopsis\": \"When brain surgery makes a mouse into a genius, dull-witted Charlie Gordon wonders if it might also work for him.  It does ... but then the mouse begins to regress.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Thirteen Reasons Why\",\n" +
                    "    \"author\": \"Jay Asher\",\n" +
                    "    \"synopsis\": \"When high school student Clay Jenkins receives a box in the mail containing 13 cassette tapes recorded by his classmate Hannah, who committed suicide, he spends a bewildering and heartbreaking night crisscrossing their town, listening to Hannah's voice recount the events leading up to her death.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"The Curious Incident of the Dog in the Night-Time\",\n" +
                    "    \"author\": \"Mark Haddon\",\n" +
                    "    \"synopsis\": \"Despite his overwhelming fear of interacting with people, Christopher, a mathematically gifted, autistic boy, decides to investigate the murder of a neighbor's dog and uncovers secrets about his mother.\"\n" +
                    "  }]\n" +
                    "}";

            try {
                JSONObject jsonObject = new JSONObject(students_array);
                JSONArray jsonArray = jsonObject.getJSONArray("books");
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String jBookTitle = object.getString("name");
                    String jBookAuthor = object.getString("author");
                    String jBookSynopsis = object.getString("synopsis");

                    bookId.add(i + 1);
                    bookTitle.add(jBookTitle);
                    bookAuthor.add(jBookAuthor);
                    bookSynopsis.add(jBookSynopsis);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



        RecycleAdapter ra = new RecycleAdapter(this, bookTitle, bookAuthor, bookSynopsis, bookCover);
        rView.setAdapter(ra);
        rView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.usermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuitem1){
            //Toast.makeText(this, "Button pressed.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, RequestForm.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.menuitem2){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}