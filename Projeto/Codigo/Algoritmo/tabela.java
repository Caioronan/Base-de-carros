import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
// arquivo somente para tratamento do banco de dados

public class tabela {
    public static void limparTela() throws InterruptedException, IOException {
        // Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("clear");
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {

        RandomAccessFile raf = new RandomAccessFile("dados/car_train.csv", "rw");//abre o csv
        RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw");//abre o arquivo.db
        raf.seek(0);
        des.seek(0);
        des.setLength(0);
        des.writeInt(1000);

        Scanner sc = new Scanner(System.in);
        limparTela();

        System.out.println("0- criar banco de dados\n1- criar banco invertido");//menu no banco
        int opcao = sc.nextInt();

        if (opcao == 0) {

            for (int i = 1; i <= 1000; i++) {//loop pra ler o csv

                String cvs = ""; 
                cvs = raf.readLine();// le a linha do csv
                String rafes[] = cvs.split(","); // reparte a linha pra pegar as colunas que eu quero
                String data = rafes[2]; // data

                //grava no arquivo.db
                des.writeInt(i);
                des.writeBoolean(false);
                des.writeFloat(Float.parseFloat(rafes[1]));
                des.writeUTF(data.replaceAll("-", "/"));//retira o caracter "-"
                des.writeUTF(rafes[4]);
                des.writeUTF(rafes[13]);
                des.writeUTF(rafes[14] + "," + rafes[16]);
                // mostra oque foi gravado
                System.out.println("car [id=" + i + ", price=" + Float.parseFloat(rafes[1]) + ", date=" + data
                        + ", body=" + rafes[4] + ", make=" + rafes[13]
                        + ", model=" + rafes[14] + "," + rafes[16] + "]");

            }
        }
        if (opcao == 1) {

            for (int i = 1000; i >= 1; i--) {//loop pra ler o csv

                String cvs = ""; 
                cvs = raf.readLine();// le a linha do csv
                String rafes[] = cvs.split(","); // reparte a linha pra pegar as colunas que eu quero
                String data = rafes[2]; // data

                //grava no arquivo.db
                des.writeInt(i);
                des.writeBoolean(false);
                des.writeFloat(Float.parseFloat(rafes[1]));
                des.writeUTF(data.replaceAll("-", "/"));//retira o caracter "-"
                des.writeUTF(rafes[4]);
                des.writeUTF(rafes[13]);
                des.writeUTF(rafes[14] + "," + rafes[16]);
                // mostra oque foi gravado
                System.out.println("car [id=" + i + ", price=" + Float.parseFloat(rafes[1]) + ", date=" + data
                        + ", body=" + rafes[4] + ", make=" + rafes[13]
                        + ", model=" + rafes[14] + "," + rafes[16] + "]");

            }
        }

        sc.close();
        des.close();
        raf.close();

    }

}
