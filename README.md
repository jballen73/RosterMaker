# RosterMaker
So you want to automatically make the evening activity rosters?  Well here’s how:

The Spreadsheet:
The spreadsheet must be set up in a very particular way in order for the jar to work properly.  The rest of the spreadsheets should be set up properly, but just for reference, here’s how they need to look:
[Demo](example.png)
Row 1: The Activity Choices:  Filling these in in the green highlighted spaces to the side will automatically fill this area correctly.  Note: If there are more than 6 activities, this will not work correctly, but it is easily fixable to do so.
Row 2: The Meeting Places: Again, these will automatically fill if you fill in the area on the side.
Row 3: Capacity: See rows 1 and 2
Row 4: A header row that doesn’t matter
Row 5: The first RC, with the rest of the students and RC’s below them

The takeaway from this: Fill in the area on the side (as per usual) and everything will automatically fill in as necessary.  Don’t add new rows or put text in those first 3 rows other than what will fill in automatically.  Blank activities will be dealt with automatically.

Preparing the Spreadsheet:
A small amount of work needs to be done in order to prepare the spreadsheet for use by the jar.
1:  Every RC who does not have RC Group Night for that day needs to have “RC” typed in the third column (the first choice column), all capitals with no other text.  Typing this for RC’s with group night should not break anything, but is unnecessary.
2:  Every RC who has priority for that day’s evening activities should have “P” typed in the 4th column (the second choice column), again capital with no other text.

That’s it.  Now you can download the spreadsheet as a CSV (File->Download As-> Comma-Separated Values) and get to work!

Using the jar file:
First, you must download the spreadsheet as a CSV (explained above), and download the jar file (RosterMaker.jar).  Move them to the same directory (folder) that is preferably not buried within a bunch of other folders.  Then open up Command Line (Terminal on Mac or Linux).  You will now need to go to the directory containing the jar and csv.  Use the “cd” command to change directories step by step until you arrive in the folder containing the files.  Tab to autocomplete will be your friend.  If you go to far, use “cd ..” to back out one layer.  If you’re having too much trouble navigating with command line, simply move the jar and csv into the directory your command line is currently in.

The Magic:
Enter the following command into your command line:
java -jar RosterMaker.jar SPREADSHEETFILEPUTNAMEHERE.csv

Obviously that should be replaced with the name of the csv file.  If you type the first letter of the file, you should be able to hit tab repeatedly until the correct name autocompletes.  You must have at least Java 8 on your computer for this step to work.

What next?
You did it.  You should have a file in that same directory called Assignment.docx containing the rosters.  I recommend renaming this file to something a bit more specific and reformatting some of the tables to look nicer, but regardless you have the roster now.
