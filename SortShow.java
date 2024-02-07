/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//just a new comment to test commit
// just another comment

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}



		
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

			//You need to complete this part.
			//goes through each element in the length lines array
			for(int index = 0; index < total_number_of_lines -1; index++)
			{
				//locates the next smallest int that is after index
				int indexOfNextSmallest = getIndexOfSmallest(index, total_number_of_lines -1);
				//swaps the next smallest int with the current index
				swap(index, indexOfNextSmallest);
			}


			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
			SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){

			//You need to complete this part.
			//sets the min to the first argument/ initializes
			int min = lines_lengths[first];
			// initializes the index of first
			int indexOfMin = first;
			//goes through array lines-lengths from the indices passed in
			for(int index = first +1; index <= last; index++)
			{
				//if a lower int is found it sets min to that values and sets the index to correspond
				if(lines_lengths[index] < min)
				{
					min = lines_lengths[index];
					indexOfMin = index;
				}
				paintComponent(this.getGraphics());
			}

			return indexOfMin; //passes back the index of the min value found inbetween the indices passed in
		}
		
	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();

			//calling recursive merge sort method
			R_MergeSort(0, total_number_of_lines-1);

			Calendar end = Calendar.getInstance();
			//getting the time it took for the iterative merge sort to execute
			//subtracting the end time with the start time
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
			if(first < last){ //when first < last is false, we are done splitting the array

				int mid = (first+last)/2; //middle of each subarray
				R_MergeSort(first, mid); //recursive call on left half
				R_MergeSort(mid+1, last); //recurive call on right half
				R_Merge(first, mid, last); //method to merge subarrays
				paintComponent(this.getGraphics()); //update GUI after each merge
				//Causing a delay for 10ms
				delay(10); 
			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			int size_one = mid - first + 1; //size of left subarray
			int size_two = last - mid; //size of right subarray

			int[] leftArray = new int[size_one]; //create new temp array for left
			int[] rightArray = new int[size_two]; //create new temp array for right


			for(int i = 0; i < size_one; ++i){ // transfer to left temp array from original array
				leftArray[i] = lines_lengths[first + i];
			}
			for(int j = 0; j < size_two; ++j){ //transfer to right temp array from original array
				rightArray[j] = lines_lengths[mid + 1+ j];
			}

			int i = 0; //index for left array
			int j = 0; //index for right array
			int k = first; //index for array that will be sorted

			while(i < size_one && j < size_two){ //loop that ends when we get to end of one of the subarrays
				if(leftArray[i] <= rightArray[j]){ //if left[i] <= right[j] insert left[i] at this point in array
					lines_lengths[k] = leftArray[i];
					i++; //increment left index
				}
				else{
					lines_lengths[k] = rightArray[j]; //else insert right[j] at current index
					j++; //increment right index
				}
				k++; //increment index of original array
			}

			while(size_one > i){ //make sure right array is empty, if not, all elements should already be in sorted order and we can add to end
				lines_lengths[k] = leftArray[i];
				i++;
				k++;
			}

			while(size_two > j){ //make sure left array is empty, if not, all elements should already be in sorted order and we can add to end
				lines_lengths[k] = rightArray[j];
				j++;
				k++;
			}
			paintComponent(this.getGraphics()); //update GUI

		}
		
		//

	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort()
		{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}
	/////////////////////////////////////////
		public void BubbleSort(){
			//getting the date and time when the bubble sort starts
			Calendar start = Calendar.getInstance();
			//Using the bubble sort to lines_lengths sort the array

			bubble_Sort(total_number_of_lines);
			paintComponent(this.getGraphics());
			//getting the date and time when the bubble sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the bubble sort to execute
			//subtracting the end time with the start time
			SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
		}

		public void bubble_Sort(int n){ //method to sort using bubble sort, takes an integer of size n which is size of the array
			for(int lastIndex = n-1; lastIndex > 0; lastIndex--){ //begin at last index of array and move towards the front of the array
				int lastSwapIndex = 0; //part of betterBubbleSort, we can keep track of most recent swap in inner loop
				for(int i = 0; i < lastIndex; i++){ //this loop is where we compare and swap elements
					if(lines_lengths[i] > lines_lengths[i+1]) { //check and see if value at index i is greater than value at index i+1, if true, swap
						swap(i, i + 1); //simple swap method that swaps elements at i and i+1
						lastSwapIndex = i; //keeps track of most recent swap to get better performance, doesn't look at already sorted elements
						paintComponent(this.getGraphics()); //update GUI
					}
					}
				lastIndex = lastSwapIndex + 1; //updates bounds so that we don't make unnecessary comparisons
				}
			}









		///////////////////////////////////////////////////////
		public void InsertionSort(){
			//getting the date and time when the insertion sort starts
			Calendar start = Calendar.getInstance();
			//Using the insertion sort to lines_lengths sort the array

			//You need to complete this part.

			//getting the date and time when the insertion sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the insertion sort to execute
			//subtracting the end time with the start time
			SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		////////////////////////////////////////////////////////////////////////
		public void ShellSort(){
			//getting the date and time when the shell sort starts
			Calendar start = Calendar.getInstance();
			//Using the shell sort to lines_lengths sort the array

			//You need to complete this part.

			//getting the date and time when the shell sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the shell sort to execute
			//subtracting the end time with the start time
			SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
		}
		////////////////////////////////////////////////////////////////////
		public void QuickSort(){
			//getting the date and time when the quick sort starts
			Calendar start = Calendar.getInstance();
			//Using the quick sort to lines_lengths sort the array

			//You need to complete this part.
			//Calls the recursive function for quicksort
			quicksort(lines_lengths,0,total_number_of_lines -1);
			//updates gui
			paintComponent(this.getGraphics());

			//getting the date and time when the quick sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the quick sort to execute
			//subtracting the end time with the start time
			SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
		}

		private static final int CUTOFF = 3; //cutoff component, once array is sorted in sets of 3 it will call insertion
												//sort to finish the sorting
		//returns the median of the left center and right int provided
		private int median3( int[] a, int left, int right)
		{
			int center = (left + right )/2; //sets a center to be the int middle of the left and right
			if(a[center] < a[left]) //swaps left to the middle if it is greater than the center
				swap(left, center);
			if(a[right] < a[left]) // swaps left to the right if left is greater than right
				swap(left,right);
			if(a[right] < a[center]) // swaps center to the right if it is greater than the right
				swap(center,right);
			swap(center, right-1); //places the pivot at right-1
			paintComponent(this.getGraphics()); //updates gui
			return a[right -1]; //returns the median
		}

		//recursive call for quicksort, uses a median of three and a cutoff of three
		private void quicksort(int [] a, int left, int right)
		{
			if( left + CUTOFF <= right)
			{
				int pivot = median3(a, left, right); // sets the pivot value

				//begins the partitioning
				int i = left, j = right -1;
				for( ; ; )
				{
					while(a[++i] < pivot){} //goes through entire array
					while(a[--j] > pivot){}
					if(i<j) {
						swap(i, j);
						paintComponent(this.getGraphics()); //updates gui
					}
					else
						break;
				}
				swap(i, right-1);
				paintComponent(this.getGraphics());

				quicksort(a,left, i-1);  //recursive calls
				quicksort(a, i+1, right);

			}
			else
				insertionSort(a,left,right); //once sets are sorted up until the sets of 3 it will call
												//insertion sort to finish the sorting of the sets of 3

		}

		//basic insertion sort that is called from quicksort
		public void insertionSort(int [] a, int left, int right)
		{
			for(int p = left+1; p<= right; p++)
			{
				int tmp = a[p];
				int j;
				for(j = p; j> left && tmp < a[j-1]; j--)
				{
					a[j] = a[j-1];
					paintComponent(this.getGraphics());
				}
				a[j]=tmp;
				paintComponent(this.getGraphics());
			}
		}
	//////////////////////////////////////////////////////////////////////	
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
			}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

