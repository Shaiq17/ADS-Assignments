  package DSA_LAB;

public class ImageLabel {

    static int image[][] = {
        {1,1,0,0},
        {1,0,0,1},
        {0,0,1,1}
    };

    static int rows = 3;
    static int cols = 4;

    static void labelComponents() {

        CircularQueue<int[]> q = new CircularQueue<>(rows * cols);

        int label = 2;

        int dx[] = {-1,1,0,0};
        int dy[] = {0,0,-1,1};

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (image[i][j] == 1) {

                    q.enqueue(new int[]{i,j});
                    image[i][j] = label;

                    while (!q.isEmpty()) {

                        int p[] = q.dequeue();

                        for (int k = 0; k < 4; k++) {

                            int x = p[0] + dx[k];
                            int y = p[1] + dy[k];

                            if (x>=0 && y>=0 && x<rows && y<cols && image[x][y]==1) {

                                image[x][y] = label;
                                q.enqueue(new int[]{x,y});
                            }
                        }
                    }

                    label++;
                }
            }
        }
    }

    public static void main(String[] args) {

        labelComponents();

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++)
                System.out.print(image[i][j]+" ");
            System.out.println();
        }
    }
}