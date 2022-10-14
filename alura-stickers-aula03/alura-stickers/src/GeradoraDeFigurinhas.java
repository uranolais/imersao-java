// import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
// import java.io.BufferedInputStream;
import java.io.File;
// import java.io.FileInputStream;
import java.io.InputStream;
//import java.net.URL;

import javax.imageio.ImageIO;
// import javax.print.attribute.standard.PageRanges;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem
        
        //InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparencia e com tamanho novo
       
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        //int novaAltura = altura + 200;//200px
       // int novaAltura = (int)(altura*1.15);
        int alturaFundoParaTexto = 200;
        int novaAltura = altura + alturaFundoParaTexto;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para a nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null); //onde na imagem eu vou desenhar

        // escrever uma frase na nova imagem
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        FontMetrics medidasFonte = graphics.getFontMetrics(fonte);
        int alturaFonte = medidasFonte.getHeight();
        int larguraFonte = medidasFonte.stringWidth("TOPZERA");
       //int tamanhoFonte = (int) (altura*0.08);
       //int tamanhoFonte = 64;
       //var fonte = new Font(Font.SANS_SERIF, Font.BOLD, tamanhoFonte);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);
        int meioHorizontal = (largura - larguraFonte)/2;
        int meioVertical = (int) (novaAltura - (alturaFundoParaTexto - alturaFonte/2)/2);

        graphics.drawString("TOPZERA", meioHorizontal, meioVertical);
        //graphics.drawString("TOPZERA",100,novaAltura-200);
         
        // escrever a nova imagem em um arquivo 
        
        //var saida = "saida/" + nomeArquivo;
        File figurinha = new File(nomeArquivo);
        if(figurinha.mkdirs())
            ImageIO.write(novaImagem, "png", figurinha);
        //ImageIO.write(novaImagem, "png", new File(saida));
        //ImageIO.write(novaImagem,"png", new File(nomeArquivo));
        //graphics.dispose();
    }

    //public static void main(String[] args) throws Exception {
    //    GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
     //   geradora.cria();
    //}

}
