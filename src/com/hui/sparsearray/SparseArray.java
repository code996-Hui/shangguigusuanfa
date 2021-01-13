package com.hui.sparsearray;

import java.io.*;

/**
 * @author 姚晶辉
 * @Description 稀疏数组的应用 将二维数组转为稀疏数组保存
 *              到本地并读取本地文件转为二维数组
 * @date 2020/12/9 14:08
 * @Version 1.0
 */
public class SparseArray {
    public static void main(String[] args) {
        //region 创建一个二维组数  chessArr1
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 5;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int num : chessArr1[i]) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }

        //endregion

        //region 构建稀疏矩阵 sparseArr1
        //将数组转化为稀疏矩阵sparseArr1
        //统计二维数组中非0的个数
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                if (anInt != 0) sum++;
            }
        }

        int[][] sparseArr1 = new int[sum + 1][3];
        //给稀疏矩阵赋值
        sparseArr1[0][0] = chessArr1.length;
        sparseArr1[0][1] = chessArr1[0].length;
        sparseArr1[0][2] = sum;
        int count =0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr1[count][0]=i;
                    sparseArr1[count][1]=j;
                    sparseArr1[count][2]=chessArr1[i][j];
                }
            }
        }
        //打印对应的稀疏矩阵
        System.out.println("这是构建好的系数矩阵：");
        for (int[] ints : sparseArr1) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        //endregion

        //写入磁盘
        File file = new File("D:\\workspace\\shangguigu\\gui-suanfa\\src\\data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("创建文件成功");
            } catch (IOException e) {
                System.out.println("创建文件失败");
            }
        }
        FileWriter fw = null;
        try {
            fw=new FileWriter(file);
            for (int[] ints : sparseArr1) {
                for (int anInt : ints) {
                    fw.write(anInt+"\t");
                }
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("写入文件成功============");
        BufferedReader br=null;
        int [][] sparseArr2 = new int[sum+1][3];
        int coun_row = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line=br.readLine())!=null){
                String []temp=line.split("\t");
                for (int i = 0; i < temp.length; i++) {
                    sparseArr2[coun_row][i]=Integer.parseInt(temp[i]);
                }
                coun_row++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("读入成功，现在遍历的是读入的稀疏矩阵");
        for (int[] ints : sparseArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

        System.out.println("遍历结束");



        //region 稀疏矩阵还原为二维数组
        int[][] chessArr2 = new int[chessArr1.length][chessArr1[0].length];
        //遍历稀疏矩阵，给二维数组赋值
        for (int i = 1; i < sparseArr1.length; i++) {
           chessArr2[sparseArr1[i][0]][sparseArr1[i][1]]=sparseArr1[i][2];
        }
        System.out.println("这是还原后的二维矩阵：");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        //endregion


    }
}
