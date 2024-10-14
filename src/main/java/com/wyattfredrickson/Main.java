package com.wyattfredrickson;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Main class that will run the program and display the user interface to the user.
 */
public class Main {
    List<Bands> bandList = new ArrayList<>(); // Create a list to store the bands from the text file


    /**
     * Method that will display the developer information to the user.
     * 
     */
    public void displayDeveloperInfo() {
        // Display the developer information
        System.out.println("Submitted by: Wyatt Fredrickson - wfredric@csp.edu");
        System.out.println("I certify that this is my own work");
    }


    /**
     * Method that will load the bands from the text file and store in a list List<Bands>
     * @param fileName the name of the file to load the bands from
     */
    public void loadBandsFromFile(String fileName) {
        // Load the bands from the text file and store in a list List<Bands>
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the line into parts using '|' symbol
                
                if (parts.length == 2) {
                    String bandName = parts[0].trim(); // Get the band name
                    Float setTime = Float.parseFloat(parts[1].trim()); // Get the set time of the band
                    Bands band = new Bands(bandName, setTime); // Create a new Bands object
                    bandList.add(band); // Add the band to the list
                } else {
                    System.out.println("Invalid data in file: " + line);
                }
            }
            System.out.println("The bands have been loaded from the file successfully.");
        }   catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName); // Catch the file not found exception
        } catch (IOException e) {
            System.out.println("An error has occured while reading the file: " + fileName); // Catch the IO exception
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + fileName); // Catch the number format exception
        }
    }


    /**
     * Method, with helpers that will sort the bands by band name using O(log n) sorting logorithmic algorithm
     */
    public void sortBandsByBandName() {
        quickSort(0, bandList.size() - 1); // Call the quicksort method to sort the bands by band name
        // - 1 because the index starts from 0
    }
    // Helper method for sorting the bands by band name using quicksort algorithm
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high); // Partition the array into two parts using the pivot index

            quickSort(low, pivotIndex - 1); // Sort the left side of the array
            quickSort(pivotIndex + 1, high); // Sort the right side of the array
        }
    }
    // Another helper method for sorting the bands by band name using quicksort algorithm
    private int partition(int low, int high) {
        Bands pivot = bandList.get(high); // Get the pivot element from the high index
        int i = low - 1; // Index of the smaller element
        // - 1 because the index starts from 0
        // Loop through the array to compare the elements with the pivot element
        for (int j = low; j < high; j++) {
        if (bandList.get(j).getBandName().compareTo(pivot.getBandName()) < 0) {
            i++; // Increment the index of the smaller element
            swap(i, j); // Swap the elements
            }
        }
        swap(i + 1, high); // Swap the elements
        return i + 1; // Return the index of the pivot element
    }
    // Another helper method that swaps the elements in the array
    private void swap(int i, int j) {
        Bands temporary = bandList.get(i); // Get the temporary element
        bandList.set(i, bandList.get(j)); // Set the element at index i to the element at index j
        bandList.set(j, temporary); // Set the element at index j to the temporary element
    }


    /**
     * Method with helpers that will sort the bands by set time using O(n) sorting linear algorithm
     */
    public void sortBandsBySetTime() {
        quickSortSetTime(0, bandList.size() - 1); // Call the quicksort method to sort the bands by set time
        // - 1 because the index starts from 0
    }
    // Helper method for sorting the bands 
    private void quickSortSetTime(int low, int high) {
        if (low < high) {
            int pivotIndex = partitionSetTime(low, high); // This will partition the array into two parts using the pivot index
            quickSortSetTime(low, pivotIndex - 1); // This will sort the left side of the array
            quickSortSetTime(pivotIndex + 1, high); // This will sort the right side of the array
        }
    }
    // Helper method that will partition the array into two parts, one is low and the other is high
    private int partitionSetTime(int low, int high) {
        Bands pivot = bandList.get(high); // Gets the pivot element from the high index
        int i = low - 1; // Index of the smaller element
        for (int j = low; j < high; j++) { // This will use for loop to compare the elements with the pivot element
            if (bandList.get(j).getSetTime() < pivot.getSetTime()) { // If the element is less than the pivot element
                i++; // Increment index of the smaller element
                swap(i, j); // Swap the elements
            }
        }
        swap(i + 1, high); // Swap the elements
        return i + 1; // Return the index of the pivot element
    }


    /**
     * Method that will search for a band by band name using a BINARY search algorithm.
     * Search for a band by band name, binary search using O(log n) logarithmic algorithm 
     * @return the band with the band name
     */
    public Bands searchByBandName(String targetBandName) {
        return binarySearch(0, bandList.size() - 1, targetBandName); // Calls the binary search method to search for a band by band name
        // - 1 because the index starts from 0
    }
    //
    private Bands binarySearch(int low, int high, String targetBandName) {
        if (low <= high) {
            int mid = (low + high) / 2; // Calculate the middle index by adding the low and high index and dividing by 2
            Bands theMidBand = bandList.get(mid); // Get the band at the middle index of the ArrayList

            if (theMidBand.getBandName().equalsIgnoreCase(targetBandName.trim())) {
                return theMidBand; // Returns the band at the middle index of the ArrayList
            } else if (theMidBand.getBandName().compareToIgnoreCase(targetBandName.trim()) > 0) {
                return binarySearch(low, mid - 1, targetBandName); // return recursive call by searching the left side of the arrayList
            } else {
                return binarySearch(mid + 1, high, targetBandName); // return recursive call by searching the right side of the arrayList
            }
        }
        return null; // Returns null if the band is not found
    }


    /**
     * Method that will search for a band by set time using a LINEAR search algorithm.
     * Search for a band by set time, binary search using O(n) linear algorithm
     * @param targetSetTime the target set time to search for
     * @return the band with the closest set time to the target set time
     */
    public Bands searchBySetTime(float targetSetTime) {
        Bands closestBand = null; // Create a variable to store the closest band
        float closestDifference = Float.MAX_VALUE; // Create a variable to store the closest difference

        for (Bands band : bandList) { // Loop through the band list
            float difference = Math.abs(band.getSetTime() - targetSetTime); // Calculating the set time difference between the band and the target set time
            if (difference < closestDifference) { // If the difference is less than the closest difference
                closestDifference = difference; // Set the closest difference
                closestBand = band; // Set the closest band
            }
            
        }
        return closestBand; // Return the closest band
    }


    /**
     * Method that will display the user interface to the user to make a selection.
     * 
     */
    private void userInterface() {
        // Prompt the user to search by band name or set time
        // Call the searchByBandName() or searchBySetTime() method based on user input
        // Display the band information to the user.
        Scanner scanner = new Scanner(System.in);

            while (true) { // Loop to display the user interface to the user
                System.out.println("Search by Band Name (1) or Set List (2):");
                System.out.println("Enter (3) to exit the application.");
                int userInput = scanner.nextInt(); // Get the user input
                scanner.nextLine(); // Consume the newline character 

                if (userInput == 1) {
                    System.out.println("Enter Band Name you are looking for:");
                    String bandName = scanner.nextLine().trim(); // Get the band name from the user
                    Bands foundBand = searchByBandName(bandName); // Search for a band by band name

                    System.out.println("Band name is: " + bandName); // Display the band name to the user
                    if (foundBand != null) {
                        System.out.println("Band found is: " + foundBand.getBandName() + " has a set time of " + foundBand.getSetTime() + " minutes."); // Display the band information to the user
                    } else {
                        System.out.println("Band [" + bandName + "] not found."); // Display the band not found message to the user
                    }
                } else if (userInput == 2) {
                    System.out.println("Enter the Set Time you are looking for:"); 
                    float setTime = scanner.nextFloat(); // Get the set time from the user
                    Bands closestBand = searchBySetTime(setTime); // Search for a band by set time
                    System.out.println("Band with closest set time is: " + closestBand.getBandName() + " has a set time of " + closestBand.getSetTime() + " minutes."); // Display the band information to the user
                } else if (userInput == 3) {
                    System.out.println("Exiting the application..."); // Display the exit message
                    break; // Break the loop
                } else {
                    System.out.println("Thats an invalid input. Please try again."); // Display the invalid input message to the user
            }
        }
        scanner.close(); // Close the scanner
    }


    /**
     * The main method that will run the program.
     * @param args Returns arguments.
     */
    public static void main( String[] args ) {

        Main main = new Main(); // Create a new Main object for invoking the methods
        
        main.displayDeveloperInfo(); // Display the developer information to the user
        main.loadBandsFromFile("bandinfo.txt"); // Load the bands from the text file and store in a list List<Bands>
        main.sortBandsByBandName(); // Sort the bands by band name
        main.userInterface(); // Display the user interface to the user to make a selection

    }
}