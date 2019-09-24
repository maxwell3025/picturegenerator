package picturegenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class picturegenerator  {

	public static void main(String[] args) {
		int shade = 0;
		int[][] example = fractal(8, 1);
		int[][] newex = new int[example.length][example.length];
		boolean[][] stuff= new boolean[example.length][example.length];
		//newex=transform(example);
		//stuff=transform2(example);
		BufferedImage bufferedimage = new BufferedImage(example.length, example.length, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedimage.createGraphics();
		for (int x = 0; x < example.length; x++) {
			for (int y = 0; y < example.length; y++) {
				System.out.print(example[x][y]+",");
				shade = example[x][y]-1;
				
					graphics.setColor(new Color(shade, shade, shade));
				
 
				graphics.fillRect(x, y, 1, 1);

			}
			System.out.println("");
		}
try {
	ImageIO.write(bufferedimage, "png", new File("C:/image.png"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

	public static int[][] fractal(int step, int offset) {
		if (step == 0) {
			int[][] blank = { { offset } };
			return blank;
		} else {
			int[][] a = fractal(step - 1, 0);
			int[][] b = fractal(step - 1, a.length);
			int[][] c = new int[a.length * 2][a.length * 2];
			for (int x = 0; x < a.length * 2; x++) {
				for (int y = 0; y < a.length * 2; y++) {
					if (x >= a.length == y >= a.length) {
						c[x][y] = a[y % a.length][x % a.length] + offset;
					} else {
						c[x][y] = b[y % a.length][x % a.length] + offset;
					}
				}
			}
			return c;
		}
	}

	public static int[][] rotate(int[][] input, int rotation) {
		int[][] result = new int[input[0].length][input.length];
		for (int x = 0; x < input.length; x++) {
			for (int y = 0; y < input[0].length; y++) {
				result[x][y] = input[(input[0].length - 1) - y][x];
			}

		}
		if (rotation > 1) {
			return rotate(result, rotation - 1);
		} else {

			return result;
		}
	}
	public static int[][] transform(int[][]input){
		double newx=0;
		double newy=0;
		int[][] res = new int[input.length*2][input.length*2];
		for(double x = -1; x<1; x+=(double)2/input.length){
			for(double y = -1; y<1; y+=(double)2/input.length){
				newx=x*x*x-3*x*y*y;
				newy=y*y*y+3*x*x*y;
				try{
				res[(int) ((newx+1)*input.length)][(int) ((newy+1)*input.length)]=input[(int) ((x+1)*input.length)][(int) ((y+1)*input.length)];
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println(x+""+y);
				}
				}
		}
		return res;
	}
	public static boolean[][] transform2(int[][]input){
		double newx=0;
		double newy=0;
		boolean[][] res = new boolean[input.length*2][input.length*2];
		for(double x = -1; x<1; x+=(double)2/input.length){
			for(double y = -1; y<1; y+=(double)2/input.length){
				newx=x*x*x-3*x*y*y;
				newy=y*y*y+3*x*x*y;
				try{
				res[(int) ((newx+1)*input.length)][(int) ((newy+1)*input.length)]=true;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println(x+""+y);
				}
				}
		}
		return res;
	}
}
