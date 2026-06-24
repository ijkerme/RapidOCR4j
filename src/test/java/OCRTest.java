import io.github.hzkitty.RapidOCR;
import io.github.hzkitty.entity.*;
import io.github.hzkitty.utils.OpencvLoader;
import io.github.hzkitty.utils.VisRes;
import io.github.hzkitty.utils.WordBoxMerger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class OCRTest {

    static {
        OpencvLoader.loaded = true;
    }

    @Test
    public void testPath() throws Exception {
        OcrConfig config = new OcrConfig();
        // config.Det.setModelPath("C:\\ocr\\models\\v5\\Multilingual_PP-OCRv3_det_infer.onnx");
        // config.Rec.setModelPath("C:\\ocr\\models\\v5\\latin_PP-OCRv5_rec_mobile_infer.onnx");

//        config.Det.setModelPath("C:\\ocr\\models\\v5\\ch_PP-OCRv5_mobile_det.onnx");
//        config.Rec.setModelPath("C:\\ocr\\models\\v5\\ch_PP-OCRv5_rec_mobile_infer.onnx");
        RapidOCR rapidOCR = RapidOCR.create(config);
        File file = new File("src/test/resources/test_02.jpg");
        String imgContent = file.getAbsolutePath();
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setReturnWordBox(true);
        paramConfig.setUseCls(false);
        OcrResult ocrResult = rapidOCR.run(imgContent, paramConfig);
        Assertions.assertFalse(ocrResult.getRecRes().isEmpty());

//        List<RecResult> recRes = ocrResult.getRecRes();
//        for (RecResult recRe : recRes) {
//            WordBoxResult original = recRe.getWordBoxResult();
//            WordBoxResult merged = WordBoxMerger.mergeEnglishWords(original);
//            recRe.setWordBoxResult(merged);
//        }

//        VisRes vis = new VisRes();
//        // Mat visImg = vis.run(imgContent, ocrResult.getRecRes(), "src/test/resources/FZYTK.TTF");
//        Mat visImg = vis.runWord(imgContent, recRes, "src/test/resources/FZYTK.TTF");
//        Imgcodecs.imwrite("vis_result_word_latin.jpg", visImg);
    }

    @Test
    public void testBufferedImage() throws Exception {
        RapidOCR rapidOCR = RapidOCR.create();
        File file = new File("src/test/resources/text_01.png");
        BufferedImage imgContent = ImageIO.read(file);

        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setReturnWordBox(true);
        OcrResult ocrResult = rapidOCR.run(imgContent, paramConfig);
        Assertions.assertFalse(ocrResult.getRecRes().isEmpty());
        System.out.println(ocrResult);
    }

    @Test
    public void testByte() throws Exception {
        RapidOCR rapidOCR = RapidOCR.create();
        File file = new File("src/test/resources/text_01.png");
        byte[] imgContent = Files.readAllBytes(file.toPath());
        OcrResult ocrResult = rapidOCR.run(imgContent);
        Assertions.assertFalse(ocrResult.getRecRes().isEmpty());
        System.out.println(ocrResult);
    }

    @Test
    public void testMat() throws Exception {
        RapidOCR rapidOCR = RapidOCR.create();
        File file = new File("src/test/resources/text_01.png");
        Mat imgContent = Imgcodecs.imread(file.getAbsolutePath());
        OcrResult ocrResult = rapidOCR.run(imgContent);
        Assertions.assertFalse(ocrResult.getRecRes().isEmpty());
        System.out.println(ocrResult);
    }

}
