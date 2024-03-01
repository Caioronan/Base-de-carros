import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class crud {
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
    limparTela();

    Scanner sc = new Scanner(System.in);// abre o scanner1

    // so as opçoes de escolha
    int opcao = 1;
    while (opcao < 9 && opcao > 0) {
      System.out.println(
"\nseja bem vindo ao mais melhor CRUD de todos\nescolha sua opçao: \n\n 1- C reate\t5- O rneção externa\n 2- R ead\t6- H ashing estendido\n 3- U pdate\t7- A vore B+\n 4- D elete\t8- L ista Invertida\n--------------------------------------------");
      System.out.println(" 10- Huffman\t 12- KMP\n 11- LZW\t 13- Boyer Moore\n14 - RSA\n\n 0- Q uit");
opcao = sc.nextInt();
    

      switch (opcao)// comandos
      {
        case 1:
        limparTela();
          Create();
          break;
        case 2:
        limparTela();
          Read();
          break;
        case 3:
        limparTela();
          Update();
          break;
        case 4:
        limparTela();
          Delete();
          break;
          case 5:
          limparTela();
          System.out.println("nao esta pronto");
          ordenacao();
          break;
          case 6:
          limparTela();
          System.out.println("nao esta pronto");
          hashing();
          break;
          case 7:
          limparTela();
          System.out.println("nao esta pronto");
          arvore();
          break;
          case 8:
          limparTela();
          System.out.println("nao esta pronto");
          listaInv();
          break;
          case 10:
          limparTela();
         /*Huffman(); */ 
          break;
          case 11:
          limparTela();
          LZW();
          break;
          case 12:
          limparTela();
          KMP();
          break;
          case 13:
          limparTela();
          Boyer();
          break;
          case 14:
          limparTela();
          RSA();
          break;
        default:
          System.out.println("tchau");
          break;
      }
    }

    sc.close();
  }
  public static String texto () throws IOException
  {
      String text = "";
      try (RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw")) {
        Scanner sc = new Scanner(System.in);
        des.seek(0);

        int ids = des.readInt();// o primeiro inteiro do arquivo contem a quantidade de registros
        
          for (int i = 0; i < ids; i++) {// passa sequencialmente pelo arquivo
            car carrinho = new car();

            carrinho.id = des.readInt();
            carrinho.lapide = des.readBoolean();
            text += des.readFloat();
            text += des.readUTF();
            text += des.readUTF();
            text += des.readUTF();
            text += des.readUTF();
           
          }
      }
      return text;

        
  }
  public static String RSAcrip() throws IOException
  {
    String tabela = texto(); //recebe o a base de sados em string
    String cript = "";
    for (char letra : tabela.toCharArray()) {
       cript += (char) ((char) Math.pow(letra, 3)%33) ; // aplica a formula de criptografia
    }
    return cript; //retorna criptografado
  }
  public static String RSAdescrip(String tabela) throws IOException
  {
    String descript = "";
    for (char letra : tabela.toCharArray()) {
       descript += (char) ((char) Math.pow(letra, 7)%33) ; // aplica a formula de descriptografia
    }
    return descript;
  }
  public static void RSA () throws InterruptedException, IOException
  {
    limparTela();
    Scanner sc = new Scanner(System.in);
    System.out.print("RSA\n1 - Criptografar\n2 - Descriptografar\n0 - Quit");
    int opcao = sc.nextInt();
    long tempo = System.currentTimeMillis();

      switch (opcao) {
        case 1:
        String comp = RSAcrip();

        RandomAccessFile raf0 = new RandomAccessFile("dados/cars.db", "r");
        long TamArqOri = raf0.length();

        RandomAccessFile raf1 = new RandomAccessFile("dados/RSAcriptografado.db", "rw");
          raf1.writeChars(comp);
      long TamArq = raf1.length();
        System.out.println("tempo de execução:"+ (System.currentTimeMillis() - tempo) + " em milisegundos\ntaxa de criptografia:" + (double) TamArqOri / TamArq);
          raf1.close();

          break;
        case 2:

        RandomAccessFile raf00 = new RandomAccessFile("dados/RSAcriptografado.db", "r");
        
        long tamArq = raf00.length();
 
        String testo = "";
        while (raf00.getFilePointer() < tamArq) {
            testo += raf00.readLine();

        }
        String descomp = RSAdescrip(testo);

        RandomAccessFile raf2 = new RandomAccessFile("dados/RSAdescriptografado.db", "rw");
          raf2.writeChars(descomp);
          raf2.close();
        System.out.println("tempo de execução:"+ (System.currentTimeMillis() - tempo) + " em milisegundos");

        
          break;
      
        default:
          break;
      }
  }
/* 
  class HuffNo {
    char caracter;
    int repeticoes;
    HuffNo esq, dir;

    public HuffNo(char caracter, int repeticoes) {
        this.caracter = caracter;
        this.repeticoes = repeticoes;
        this.esq = this.dir = null;
    }
}

    public static HashMap<Character, String> codigo = new HashMap<>();

    public static void Huffman() throws InterruptedException, IOException {

    limparTela();
    Scanner sc = new Scanner(System.in);
    System.out.print("Huffman\n1 - compressão\n2 - descompressão\n0 - Quit");
    int opcao = sc.nextInt();
    long tempo = System.currentTimeMillis();


        Map<Character, Integer> tabela = tabelaHuffman(texto());
        HuffNo raiz = arvoreHuffman(tabela);

        Huffcomp(raiz, "", codigo);

        switch (opcao) {
        case 1:
        String comp = Huffcomp();

        RandomAccessFile raf1 = new RandomAccessFile("dados/Huffmancomprimido.db", "rw");
          raf1.writeChars(comp);
          raf1.close();
        System.out.println("tempo de execução:"+ (System.hmpTimeMillis() - tempo) + " em milisegundos");
          break;
        case 2:

        RandomAccessFile raf0 = new RandomAccessFile("dados/Huffmancomprimido.db", "r");
        
        long tamArq = raf0.length();
 
        String testo = "";
        while (raf0.getFilePointer() < tamArq) {
            testo += raf0.readLine();

        }
        String descomp = Huffdes(testo, raiz);

        RandomAccessFile raf2 = new RandomAccessFile("dados/Huffmandescomprimido.db", "rw");
          raf2.writeChars(descomp);
          raf2.close();
        System.out.println("tempo de execução:"+ (System.hmpTimeMillis() - tempo) + " em milisegundos");

        
          break;
      
        default:
          break;
      }
    }
    

    public static Map<Character, Integer> tabelaHuffman(String testo) {
        Map<Character, Integer> tabela = new HashMap<>();

        for (char c : testo.toCharArray()) {
            tabela.put(c, tabela.getOrDefault(c, 0) + 1);
        }

        return tabela;
    }

     public static HuffNo arvoreHuffman(Map<Character, Integer> tabela) {
        PriorityQueue<HuffNo> prio = new PriorityQueue<>((node1, node2) -> node1.repeticoes - node2.repeticoes);

        for (char key : tabela.keySet()) {
            prio.add(new HuffNo(key, tabela.get(key)));
        }

        while (prio.size() > 1) {
            HuffNo esq = prio.poll();
            HuffNo dir = prio.poll();

            HuffNo mergedNode = new HuffNo('\0', esq.repeticoes + dir.repeticoes);
            mergedNode.esq = esq;
            mergedNode.dir = dir;

            prio.add(mergedNode);
        }

        return prio.poll();
    }

    public static void Huffcomp(HuffNo raiz, String code, Map<Character, String> codigo) {
        if (raiz == null) {
            return;
        }

        if (raiz.caracter != '\0') {
            codigo.put(raiz.caracter, code);
        }

        Huffcomp(raiz.esq, code + "0", codigo);
        Huffcomp(raiz.dir, code + "1", codigo);
    }

    public static String Huffcomp() throws IOException {
        StringBuilder comp = new StringBuilder();

        for (char c : texto().toCharArray()) {
            comp.append(codigo.get(c));
        }

        return comp.toString();
    }

    public static String Huffdes(String comp, HuffNo raiz) {
        StringBuilder descomp = new StringBuilder();
        HuffNo hmp = raiz;

        for (char a : comp.toCharArray()) {
            if (a == '0') {
                hmp = hmp.esq;
            } else if (a == '1') {
                hmp = hmp.dir;
            }

            if (hmp.caracter != '\0') {
                descomp.append(hmp.caracter);
                hmp = raiz;
            }
        }

        return descomp.toString();
    }
*/


  public static String LZWcomp () throws InterruptedException, IOException {
    limparTela();
    String text = texto();

    

    Map<String, Integer> dicionario = new HashMap<>(); // cria o diciornario e coloca elementos
        int DicTam = 26;

        for (int i = 48; i < 57; i++) {
            dicionario.put("" + (char) i, i);
        }
        for (int i = 65; i < 90; i++) {
            dicionario.put("" + (char) i, i);
        }
        for (int i = 97; i < 122; i++) {
            dicionario.put("" + (char) i, i);
        }

        String apoio = "";

        StringBuilder comprimido = new StringBuilder();

        for (char ch : text.toCharArray()) {      //separa cada caracter pelo texto
            String comb = apoio + ch;
            if (dicionario.containsKey(comb)) {
                apoio = comb;
            } else {
                comprimido.append(dicionario.get(apoio)).append(" ");
                dicionario.put(comb, DicTam++);
                apoio = "" + ch;
            }
        }

        if (!apoio.equals("")) {
            comprimido.append(dicionario.get(apoio));
        }

        return comprimido.toString();
        
    
  }

  public static String LZWdes(String comp) {
    Map<Integer, String> dicionario = new HashMap<>(); // cria o dicionario e coloca alguns caracteres
    int DicTam = 256;

        for (int i = 48; i < 57; i++) {
            dicionario.put(i,"" + (char) i);
        }
        for (int i = 65; i < 90; i++) {
            dicionario.put(i,"" + (char) i);
        }
        for (int i = 97; i < 122; i++) {
            dicionario.put(i,"" + (char) i);
        }


    String[] comprimido = comp.trim().split(" "); 
    StringBuilder descomp = new StringBuilder();
    
       
    int preapoio = Integer.parseInt(comprimido[0]); // erro no parse int
    descomp.append(dicionario.get(preapoio));

    for (int i = 1; i < comprimido.length; i++) {
        int apoio = Integer.parseInt(comprimido[i]);
        String a;
        if (dicionario.containsKey(apoio)) {
            a = dicionario.get(apoio);
        } else if (apoio == DicTam) {
            a = dicionario.get(preapoio) + dicionario.get(preapoio).charAt(0);
        } else {
            throw new IllegalArgumentException("caracter bugado");
        }

        descomp.append(a);
        dicionario.put(DicTam++, dicionario.get(preapoio) + a.charAt(0));
        preapoio = apoio;
    }

    return descomp.toString();
}

  public static void LZW () throws InterruptedException, IOException
  {
    limparTela();
    Scanner sc = new Scanner(System.in);
    System.out.print("LZW\n1 - compressão\n2 - descompressão\n0 - Quit");
    int opcao = sc.nextInt();
    long tempo = System.currentTimeMillis();

      switch (opcao) {
        case 1:
        String comp = LZWcomp();

        RandomAccessFile raf0 = new RandomAccessFile("dados/cars.db", "r");
        long TamArqOri = raf0.length();

        RandomAccessFile raf1 = new RandomAccessFile("dados/LZWcomprimido.db", "rw");
          raf1.writeChars(comp);
      long TamArq = raf1.length();
        System.out.println("tempo de execução:"+ (System.currentTimeMillis() - tempo) + " em milisegundos\ntaxa de compressão:" + (double) TamArqOri / TamArq);
          raf1.close();

          break;
        case 2:

        RandomAccessFile raf00 = new RandomAccessFile("dados/LZWcomprimido.db", "r");
        
        long tamArq = raf00.length();
 
        String testo = "";
        while (raf00.getFilePointer() < tamArq) {
            testo += raf00.readLine();

        }
        String descomp = LZWdes(testo);

        RandomAccessFile raf2 = new RandomAccessFile("dados/LZWdescomprimido.db", "rw");
          raf2.writeChars(descomp);
          raf2.close();
        System.out.println("tempo de execução:"+ (System.currentTimeMillis() - tempo) + " em milisegundos");

        
          break;
      
        default:
          break;
      }
  }

  public static void KMPhash(String padrao, int M, int[] lps) {
    int len = 0;                
    int i = 1;
    lps[0] = 0;

    while (i < M) {
        if (padrao.charAt(i) == padrao.charAt(len)) {  // testa o caracter em questao
            len++;
            lps[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = lps[len - 1]; // confere e passa pro proximo se achar
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }
}

public static int[] CRuim(char[] palavra) {
  int[] CRuim = new int[256];

  for (int i = 0; i < 256; i++) {
      CRuim[i] = -1;
  }

  for (int i = 0; i < palavra.length; i++) {
      CRuim[palavra[i]] = i;
  }

  return CRuim;
}

public static void Boyer () throws InterruptedException, IOException{ 
    limparTela();

    Scanner sc = new Scanner(System.in);
    int comparacoes = 0;
  
    System.out.println("Boyer moore\ninsira o texto a ser buscado:");
    char[] palavra = sc.nextLine().toCharArray();
    char[] texto = texto().toCharArray();

        int n = texto.length;
        int m = palavra.length;

        int[] CRuim = CRuim(palavra);

        int i = 0;

        while (i <= n - m) {
            int j = m - 1;
            comparacoes ++;

            while (j >= 0 && palavra[j] == texto[i + j]) {
                comparacoes ++;        
                j--;
            }

            if (j < 0) {
                // printa a posicao e continua
                System.out.println("Encotrado em: " + i);
                int apoio = j - CRuim[texto[i + j]];
                i += Math.max(1, apoio);
            } else {
                // somente continua
                int apoio = j - CRuim[texto[i + j]];
                i += Math.max(1, apoio);
            }
        }
      System.out.println("Comparacoes: " + comparacoes);

    }

  public static void KMP () throws InterruptedException, IOException{ // Sub menu da ordenação externa
    limparTela();

    Scanner sc = new Scanner(System.in);
    int comparacoes = 0;
  
    System.out.println("KMP\ninsira o texto a ser buscado:");
    String padrao = sc.nextLine();
    String texto = texto();

    int M = padrao.length();
    int N = texto.length();

    int[] lps = new int[M];
    KMPhash(padrao, M, lps);

    int i = 0; // índice para o texto
    int j = 0; // índice para o padrão

    while (i < N) {
      comparacoes ++;
        if (padrao.charAt(j) == texto.charAt(i)) {
            j++;
            i++;
            
        }
        if (j == M) {
            System.out.println("encontrado em: " + (i - j));
            j = lps[j - 1];
        } else if (i < N && padrao.charAt(j) != texto.charAt(i)) {
            if (j != 0) {
                j = lps[j - 1];
            } else {
                i = i + 1;
            }
        }
    }

    System.out.println("Comparacoes: " + comparacoes);
    
  }
  public static void ordenacao () throws InterruptedException, IOException{ // Sub menu da ordenação externa
    limparTela();

    Scanner sc = new Scanner(System.in);
  
    System.out.printf("\tHashing\n1- Comum\n2- blocos variaveis\n3- seleçao\n\n0- voltar");
    int opcao = sc.nextInt();

    System.out.printf("digite a limitação de registros");
    int m = sc.nextInt();
    System.out.printf("digite o numero de caminhos");
    int n = sc.nextInt();

    switch (opcao)// comandos
      {
        case 1:
        limparTela();
        Ocomum(m,n);
          break;
        case 2:
        limparTela();
          break;
        case 3:
        limparTela();
          break;
        default:
          System.out.println("voltando");
          break;
      }
  }

  public static void Ocomum(int m,int n) throws FileNotFoundException{

   try (RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw")) {
    int ids = des.readInt();
    int apoio = n*m;


for (int i = 1; i < m; i++) {// cria os arquivos iniciais
  String arq = "arquivo";
  RandomAccessFile raf = new RandomAccessFile(arq+i, "rw");
  
}        
          for (int i = 0; i < ids; i++) {// passa sequencialmente pelo arquivo
            car carrinho = new car();

            carrinho.id = des.readInt();
            carrinho.lapide = des.readBoolean();
            carrinho.price = des.readFloat();
            carrinho.date = des.readUTF();
            carrinho.body = des.readUTF();
            carrinho.make = des.readUTF();
            carrinho.model = des.readUTF();


        }
  } catch (FileNotFoundException e) {
    throw e;
  } catch (IOException e) {
    e.printStackTrace();
  }


  }
   


  public static void hashing() {
  }

  public static void arvore() {
  }

  public static void listaInv() {
  }

  public static void Delete() throws IOException {
    try {
      RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw");// abre o arquivo
      Scanner sc = new Scanner(System.in);

      int ids = des.readInt();// o primeiro inteiro do arquivo contem a quantidade de registros

      System.out.println("escreva o ID do resgistro a ser deletado:");
      int id = sc.nextInt();

      if (id < 1 || id > ids)// verifica o id procurado (caso nao exista)
      {
        System.out.println("id nao existe");

      } else// verifica o id procurado (caso exista)
      {
        boolean existe = false;
        for (int i = 0; i < ids; i++) {// passa sequencialmente pelo arquivo
          car carrinho = new car();
          //recebe os resgistros
          carrinho.id = des.readInt();
          Long local = des.getFilePointer();
          carrinho.lapide = des.readBoolean();
          carrinho.price = des.readFloat();
          carrinho.date = des.readUTF();
          carrinho.body = des.readUTF();
          carrinho.make = des.readUTF();
          carrinho.model = des.readUTF();
          Long fim = des.getFilePointer();

          if (carrinho.lapide == false && carrinho.id == id) // caso nao tenha lapide e exista, mostra na tela
          {
            des.seek(local);
            System.out.println(carrinho.toString() + "\n -removido-");
            carrinho.lapide = true;
            des.writeBoolean(true);
            des.seek(fim);

            existe = true;
            break;
          }

        }
        if (existe == false)
          System.out.println("putz, o id nao foi encontrado ou esta lapidado");
      }

      // escrevendo no arquivo

      des.close();// fecha o arquivo

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void Update() throws IOException {
    try {
      RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw");// abre o arquivo
      Scanner sc = new Scanner(System.in);

      int ids = des.readInt();// o primeiro inteiro do arquivo contem a quantidade de registros
      Long final2 = des.length();

      System.out.println("escreva o ID do resgistro a ser atualizado:");
      int id = sc.nextInt();


        boolean existe = false; //ja define o boolean para lapide posteriormente

        for (int i = 0; i < ids; i++) {// passa sequencialmente pelo arquivo
          car carrinho = new car();

          carrinho.id = des.readInt();
          Long local = des.getFilePointer();//grava a posição do arquivo
          carrinho.lapide = des.readBoolean();
          carrinho.price = des.readFloat();
          carrinho.date = des.readUTF();
          carrinho.body = des.readUTF();
          carrinho.make = des.readUTF();
          carrinho.model = des.readUTF();

          if (carrinho.lapide == false && carrinho.id == id) // caso nao tenha lapide e exista, mostra na tela
          {
            car carro = new car();//recebe as novas atualizações

            System.out.printf("Price:");
            carro.price = sc.nextFloat();
            System.out.printf("caracter:");
            carro.date = sc.next();
            System.out.printf("Body:");
            carro.body = sc.next();
            System.out.printf("Make:");
            carro.make = sc.next();
            System.out.printf("Model:");
            carro.model = sc.next();

            des.seek(local);// volta a posição do arquivo, e compara os tamanhos do novo e velho resgistro

            if (carrinho.price >= carro.price
                && carrinho.date.length() >= carro.date.length()
                && carrinho.body.length() >= carro.body.length()
                && carrinho.make.length() >= carro.make.length()
                && carrinho.model.length() >= carro.model.length()) {


              // escrevendo no arquivo
              des.writeBoolean(false);
              des.writeFloat(carro.price);
              des.writeUTF(carro.date);
              des.writeUTF(carro.body);
              des.writeUTF(carro.make);
              des.writeUTF(carro.model);
              System.out.println(carrinho.toString() + "\n --atualizado--");

            }else{
                
                ids++;//aumenta o numero de resgistros
                des.writeBoolean(true);//ativa a lapide no resgistro
                des.seek(final2);//vai pro final do arquivo

                //escreve no arquivo
                des.writeInt(ids);
                des.writeBoolean(false);
                des.writeFloat(carro.price);
                des.writeUTF(carro.date);
                des.writeUTF(carro.body);
                des.writeUTF(carro.make);
                des.writeUTF(carro.model);

                System.out.println(carrinho.toString() + "\n -atualizado-");
                // atualiza a contagem de resgistros
                des.seek(0);
                des.writeInt(ids);
              
              existe = true;
            }

          }
          
        }
        if (existe == false)
            System.out.println("putz, o id nao foi encontrado ou esta lapidado");

      des.close();// fecha o arquivo

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void Read() throws IOException {
    try {
      RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw");// abre o arquivo
      Scanner sc = new Scanner(System.in);
      des.seek(0);

      int ids = des.readInt();// o primeiro inteiro do arquivo contem a quantidade de registros
      System.out.println("numero de resgistros:" + ids);

      System.out.println("caso queira ver todos digite 0\no numero do id preocurado:");
      int id = sc.nextInt();

      if (id < 1 || id > ids)// verifica o id procurado (caso nao exista)
      {
        for (int i = 0; i < ids; i++) {// passa sequencialmente pelo arquivo
          car carrinho = new car();

          carrinho.id = des.readInt();
          carrinho.lapide = des.readBoolean();
          carrinho.price = des.readFloat();
          carrinho.date = des.readUTF();
          carrinho.body = des.readUTF();
          carrinho.make = des.readUTF();
          carrinho.model = des.readUTF();
          System.out.println(carrinho.toString());

          if (carrinho.lapide == false) // caso nao tenha lapide, mostra na tela
            System.out.println(carrinho.toString());
        }

      } else// verifica o id procurado (caso exista)
      {
        boolean existe = false;
        for (int i = 0; i < ids ; i++) {// passa sequencialmente pelo arquivo
          car carrinho = new car();

          carrinho.id = des.readInt();
          carrinho.lapide = des.readBoolean();
          carrinho.price = des.readFloat();
          carrinho.date = des.readUTF();
          carrinho.body = des.readUTF();
          carrinho.make = des.readUTF();
          carrinho.model = des.readUTF();

          if (carrinho.lapide == false && carrinho.id == id) // caso nao tenha lapide e exista, mostra na tela
          {
            System.out.println(carrinho.toString());
            existe = true;
            break;
          }

        }
        if (existe == false)
          System.out.println("putz, o id nao foi encontrado ou esta lapidado");
      }

      // escrevendo no arquivo

      des.close();// fecha o arquivo

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void Create() throws IOException // criar um arquivo no db
  {
    try {
      RandomAccessFile des = new RandomAccessFile("dados/cars.db", "rw");// abre o arquivo
      Scanner sc = new Scanner(System.in);

      des.seek(0);
      int ids = des.readInt()+1;// o primeiro inteiro do arquivo contem a quantidade de registros
      Long fim = des.length();// recebe a posição do ultimo arquivod
      des.seek(fim);// joga o ponteiro no ultimo arquivo

      car carro = new car(); // recebe as informações do teclado

      carro.id = ids;
      System.out.printf("Price:");
      carro.price = sc.nextFloat();
      System.out.printf("caracter:");
      String caracter = sc.next();
      DateTimeFormatter date = DateTimeFormatter.ofPattern(caracter);// conversao de string para caracter
      carro.date = caracter;
      System.out.printf("Body:");
      carro.body = sc.next();
      System.out.printf("Make:");
      carro.make = sc.next();
      System.out.printf("Model:");
      carro.model = sc.next();

      // escrevendo no arquivo
      des.writeInt(carro.id);
      des.writeBoolean(false);
      des.writeFloat(carro.price);
      des.writeUTF(caracter);
      des.writeUTF(carro.body);
      des.writeUTF(carro.make);
      des.writeUTF(carro.model);

      des.seek(0);
      des.writeInt(ids);

      des.close();// fecha o arquivo

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}