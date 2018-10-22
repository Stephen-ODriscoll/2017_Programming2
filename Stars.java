
public class Stars {

	public static void main(String[] args) {
		
		int[] integers = {2, 3, 4, 3, 4};
		
		String[] stars = intToStars(integers);
		
		for (int i=0; i<stars.length; i++)
			System.out.println(stars[i]);
	}
	
	public static String[] intToStars(int[] integers) {
		
		String[] stars = new String[integers.length];
		
		for (int i=0; i<integers.length; i++) {
			
			for (int x=0; x<integers[i]; x++) {
				
				if (stars[i] == null)
					stars[i] = "*";
				
				else
				stars[i] += "*";
			}
		}
		return stars;
	}

}
