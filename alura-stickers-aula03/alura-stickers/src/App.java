import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
     //exibir e manipular os dados do jeito que a gente quiser
        
        //NASA
       
        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // String url = "https://mocki.io/v1/4925733d-f053-4ad3-b635-967ddb5de393";

        //IMDB
        
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_xe8675rl";
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        
        
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        for(int i=0; i < 3; i++){
            try{
            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" +conteudo.getTitulo().replace(":", "-")  + ".png";
            var geradora = new GeradoraDeFigurinhas();
            //geradora.cria(inputStream,nomeArquivo);
             
                geradora.cria(inputStream, nomeArquivo);
                System.out.println("Titulo: \033[0:1m"+conteudo.getTitulo()+"\033[0:0m");
                System.out.println();   
                
            }catch (javax.imageio.IIOException err) {
                System.out.println("Formato inválido de imagem");
            }catch (FileNotFoundException exception) {
                System.out.println("Link invalido de imagem");
                System.out.println();
            }
        }
     }
    
}

