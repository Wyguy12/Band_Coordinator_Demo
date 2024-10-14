Project Overview
The Band Coordinator Application is a program designed to manage and search for band information. 
It reads band data from a text file, stores it in memory, and allows the user to search for bands by name or set time using efficient search algorithms. 
The user can also sort the band list by either band name or set time.





Bands Class- The Bands class stores information about each band, including:
bandName: The name of the band.
setTime: The set time (performance duration) of the band.

Methods:
Getters and setters for the band name and set time.
toString(): Provides a string representation of the band's details.

Main Class - The Main class is responsible for loading the band data from a file, sorting it, and allowing the user to interact with the program.

Methods:
loadBandsFromFile(String fileName): Reads band information from a file 
(bandinfo.txt), parses the data, and stores it in a list of Bands objects.

sortBandsByBandName(): Sorts the bands by name using the QuickSort algorithm.
sortBandsBySetTime(): Sorts the bands by set time using the QuickSort algorithm.
searchByBandName(String bandName): Searches for a band by name using the Binary Search algorithm.
searchBySetTime(float setTime): Searches for the band with the closest set time using a Linear Search algorithm.
userInterface(): Provides a simple menu-driven interface for the user to search for bands by name or set time.

bandinfo.txt-
The bandinfo.txt file contains the list of bands and their set times in the following format:
Band Name | Set Time