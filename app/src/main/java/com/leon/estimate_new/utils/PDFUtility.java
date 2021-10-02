package com.leon.estimate_new.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import androidx.core.content.ContextCompat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import com.leon.estimate_new.R;
import com.leon.estimate_new.helpers.Constants;
import com.leon.estimate_new.helpers.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PDFUtility {
    public static final String PDF_ADDRESS = Environment.getExternalStorageDirectory() + File.separator +
            MyApplication.getContext().getString(R.string.pdf_folder);
    private static BaseFont BASE_FONT;
    private static Font FONT_TITLE;
    private static Font FONT_SUBTITLE;
    private static Font FONT_CELL;
    private static Font FONT_COLUMN;
    private static Font FONT_EN;
    private static final float PAGE_MARGIN = 20f;
    private static final float PADDING = 2f;
    private static final float BORDER = 1f;

    public interface OnDocumentClose {
        void onPDFDocumentClose(File file);
    }

    public static void createPdf(Context context, OnDocumentClose mCallback, List<String[]> items, boolean isPortrait) throws Exception {
        try {
            BASE_FONT = BaseFont.createFont(Constants.PDF_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        FONT_TITLE = new Font(BASE_FONT, 12, Font.NORMAL);
        FONT_SUBTITLE = new Font(BASE_FONT, 10, Font.NORMAL);
        FONT_CELL = new Font(BASE_FONT, 6, Font.NORMAL);
        FONT_COLUMN = new Font(BASE_FONT, 12, Font.NORMAL);
        FONT_EN = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        File file = new File(PDF_ADDRESS);
        if (file.exists()) {
            file.delete();
        }

        Document document = new Document();
        document.setMargins(PAGE_MARGIN, PAGE_MARGIN, PAGE_MARGIN, PAGE_MARGIN);
        document.setPageSize(isPortrait ? PageSize.A4 : PageSize.A4.rotate());

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(PDF_ADDRESS));
        pdfWriter.setFullCompression();
        pdfWriter.setPageEvent(new PageNumeration(1));
        pdfWriter.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        document.open();

        createHeader(context, document);

        document.add(createDataTable(items));

        document.add(createSignBox());

        document.close();

        try {
            pdfWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (mCallback != null) {
            mCallback.onPDFDocumentClose(file);
        }
    }

    private static void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

    private static void setMetaData(Document document) {
        document.addCreationDate();
        document.addAuthor("RAVEESH G S");
        document.addCreator("RAVEESH G S");
        document.addHeader("DEVELOPER", "RAVEESH G S");
    }

    private static void createHeader(Context context, Document document) throws Exception {
        PdfPTable table = new PdfPTable(3);
        LanguageProcessor pe = new ArabicLigaturizer();
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 7, 2});
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;
        {
            /* LEFT TOP LOGO */
            cell = new PdfPCell(new PdfPCell());
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        }

        {
            /* MIDDLE TEXT */
            Paragraph temp = new Paragraph(pe.process("شرکت آب و فاضلاب استان اصفهان"), FONT_TITLE);
            temp.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(temp);

            temp = new Paragraph(pe.process("ارزیابی"), FONT_SUBTITLE);
            temp.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(temp);

            table.addCell(cell);
        }
        /* RIGHT TOP LOGO*/
        {
            PdfPTable logoTable = new PdfPTable(1);
            logoTable.setWidthPercentage(100);
            logoTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            logoTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            logoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            Image logo = getImageFromDrawable(((BitmapDrawable) ContextCompat.getDrawable(context, R.drawable.img_menu_logo)));

            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            logoCell.setBorder(PdfPCell.NO_BORDER);

            logoTable.addCell(logoCell);

            logoCell = new PdfPCell(new Phrase("Logo Text", FONT_CELL));
            logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            logoCell.setBorder(PdfPCell.NO_BORDER);
            logoCell.setPadding(4f);
            logoTable.addCell(logoCell);

            cell = new PdfPCell(logoTable);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setUseAscender(true);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPadding(2f);
            table.addCell(cell);
        }

        document.add(table);
    }


    public static PdfPTable createDataTable(List<String[]> dataTable) throws DocumentException {
        PdfPTable table = createTable(1, new float[]{1f});

        table.addCell(createTableRow(5, PdfPCell.ALIGN_CENTER, 1f,
                BaseColor.WHITE,
                new String[]{"ستون - 5f", "ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f"}));

        table.addCell(createTableRow(5, PdfPCell.ALIGN_CENTER, 1f, BaseColor.LIGHT_GRAY,
                new String[]{"ستون - 5f", "ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f"}));

        table.addCell(createTableRow(5, PdfPCell.ALIGN_RIGHT, 1f, BaseColor.WHITE,
                new String[]{"ستون - 5f", "ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f"}));

        table.addCell(createTableRow(3, PdfPCell.ALIGN_RIGHT, new float[]{1f, 1f, 3f},
                new float[]{BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 5f", "ستون - 4f", "ستون - 3f"}));

        table.addCell(createTableRow(1, PdfPCell.ALIGN_RIGHT, 1, BaseColor.WHITE,
                new String[]{"ستون - 5f"}));

        table.addCell(createTableRow(2, PdfPCell.ALIGN_CENTER, 1f,
                BaseColor.LIGHT_GRAY,
                new String[]{"ستون - 2f", "ستون - 1f"}));
        for (int i = 0; i < 8; i++)
            table.addCell(createTableRow(4, PdfPCell.ALIGN_CENTER, new float[]{1f, 1f, 1f, 1f},
                    new float[]{BORDER, BORDER, BORDER, PdfPCell.NO_BORDER},
                    new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.LIGHT_GRAY, BaseColor.WHITE},
                    new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f"}));

        table.addCell(createTableRow(3, PdfPCell.ALIGN_RIGHT, new float[]{2f, 1f, 1f},
                new float[]{BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 5f", "ستون - 4f", "ستون - 3f"}));

        for (int i = 0; i < 8; i++)
            table.addCell(createTableRow(5, PdfPCell.ALIGN_CENTER, 1f, BaseColor.WHITE,
                    new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f", "ستون - 1f"}));
        table.addCell(createTableRow(5, PdfPCell.ALIGN_CENTER, new float[]{1f, 1f, 1f, 1f, 1f},
                new float[]{PdfPCell.NO_BORDER, PdfPCell.NO_BORDER, BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f", "ستون - 1f"}));
        table.addCell(createTableRow(5, PdfPCell.ALIGN_CENTER, new float[]{1f, 1f, 1f, 1f, 1f},
                new float[]{PdfPCell.NO_BORDER, PdfPCell.NO_BORDER, BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f", "ستون - 1f"}));

        table.addCell(createTableRow(9, PdfPCell.ALIGN_CENTER, new float[]{1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 2f},
                new float[]{BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f", "ستون - 1f", "ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f",}));
        table.addCell(createTableRow(9, PdfPCell.ALIGN_CENTER, new float[]{1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 2f},
                new float[]{BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f", "ستون - 1f", "ستون - 4f", "ستون - 3f", "ستون - 2f", "ستون - 1f",}));

        table.addCell(createTableRow(3, PdfPCell.ALIGN_RIGHT, new float[]{1f, 1f, 3f},
                new float[]{PdfPCell.NO_BORDER, PdfPCell.NO_BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f"}));

        table.addCell(createTableRow(3, PdfPCell.ALIGN_RIGHT, new float[]{1f, 1f, 3f},
                new float[]{PdfPCell.NO_BORDER, PdfPCell.NO_BORDER, PdfPCell.NO_BORDER},
                new BaseColor[]{BaseColor.WHITE, BaseColor.WHITE, BaseColor.WHITE},
                new String[]{"ستون - 4f", "ستون - 3f", "ستون - 2f"}));

        table.addCell(createTableRow(1, PdfPCell.ALIGN_RIGHT, 1, BaseColor.WHITE,
                new String[]{"ستون - 5f"}));
        return table;
    }

    private static Phrase addPhrase(String s) {
        LanguageProcessor pe = new ArabicLigaturizer();
        return new Phrase(pe.process(s), FONT_COLUMN);
    }

    private static PdfPTable createTable(int column, float[] width) throws DocumentException {
        PdfPTable table = new PdfPTable(column);
        table.setWidths(width);
        table.setWidthPercentage(100);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        return table;
    }

    private static PdfPTable createTableRow(int column, int align, float[] width, float[] border,
                                            BaseColor[] baseColors, String[] items)
            throws DocumentException {
        PdfPTable table = createTable(column, width);
        PdfPCell cell = createPdfCell();
        cell.setHorizontalAlignment(align);
        for (int i = 0; i < items.length; i++) {
            cell.setPhrase(addPhrase(items[i]));
            cell.setBorderWidthRight(border[i]);
            cell.setBackgroundColor(baseColors[i]);
            table.addCell(cell);
        }
        return table;
    }

    private static PdfPTable createTableRow(int column, int align, float width, BaseColor baseColor,
                                            String[] items)
            throws DocumentException {
        float[] widths = new float[column];
        float[] borders = new float[column];
        BaseColor[] baseColors = new BaseColor[column];
        Arrays.fill(widths, width);
        Arrays.fill(borders, BORDER);
        Arrays.fill(baseColors, baseColor);
        borders[column - 1] = PdfPCell.NO_BORDER;
        return createTableRow(column, align, widths, borders, baseColors, items);
    }

    private static PdfPCell createPdfCell() {
        PdfPCell cell = new PdfPCell();
        cell.setPaddingTop(PADDING);
        cell.setPaddingBottom(PADDING);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPTable createSignBox() throws DocumentException {
        PdfPTable outerTable = new PdfPTable(1);
        outerTable.setWidthPercentage(100);
        outerTable.getDefaultCell().setBorderWidth(BORDER);

        PdfPTable innerTable = new PdfPTable(2);
        innerTable.setWidthPercentage(100);
        innerTable.setWidths(new float[]{2, 3});
        {
            PdfPCell cell = new PdfPCell();
            Paragraph temp = new Paragraph(new Phrase("Signature of Supervisor", FONT_COLUMN));
            cell.addElement(temp);

            temp = new Paragraph(new Phrase("( RAVEESH G S )", FONT_COLUMN));
            temp.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(temp);

            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell.setBorder(PdfPCell.NO_BORDER);
            innerTable.addCell(cell);

            //ROW-4 : Content Right Aligned
            cell = new PdfPCell(new Phrase("Signature of Staff ", FONT_COLUMN));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setBorder(PdfPCell.NO_BORDER);
            innerTable.addCell(cell);
        }

        PdfPCell signRow = new PdfPCell(innerTable);
        signRow.setHorizontalAlignment(Element.ALIGN_LEFT);
//        signRow.setBorder(PdfPCell.NO_BORDER);
        outerTable.addCell(signRow);
        return outerTable;
    }
//    private static PdfPTable createSignBox() throws DocumentException {
//        PdfPTable outerTable = new PdfPTable(1);
//        outerTable.setWidthPercentage(100);
//        outerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//
//        PdfPTable innerTable = new PdfPTable(2);
//        {
//            innerTable.setWidthPercentage(100);
//            innerTable.setWidths(new float[]{1, 1});
//            innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//
//            //ROW-1 : EMPTY SPACE
//            PdfPCell cell = new PdfPCell();
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setFixedHeight(60);
//            innerTable.addCell(cell);
//
//            //ROW-2 : EMPTY SPACE
//            cell = new PdfPCell();
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setFixedHeight(60);
//            innerTable.addCell(cell);
//
//            //ROW-3 : Content Left Aligned
//            cell = new PdfPCell();
//            Paragraph temp = new Paragraph(new Phrase("Signature of Supervisor", FONT_EN));
//            cell.addElement(temp);
//
//            temp = new Paragraph(new Phrase("( RAVEESH G S )", FONT_EN));
//            temp.setPaddingTop(4f);
//            temp.setAlignment(Element.ALIGN_LEFT);
//            cell.addElement(temp);
//
//            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setPadding(4f);
//            innerTable.addCell(cell);
//
//            //ROW-4 : Content Right Aligned
//            cell = new PdfPCell(new Phrase("Signature of Staff ", FONT_EN));
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setPadding(4f);
//            innerTable.addCell(cell);
//        }
//
//        PdfPCell signRow = new PdfPCell(innerTable);
//        signRow.setHorizontalAlignment(Element.ALIGN_LEFT);
//        signRow.setBorder(PdfPCell.NO_BORDER);
//        signRow.setPadding(4f);
//
//        outerTable.addCell(signRow);
//
//        return outerTable;
//    }

    private static Image getImage(byte[] imageByte, boolean isTintingRequired) throws Exception {
        Paint paint = new Paint();
        if (isTintingRequired) {
            paint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN));
        }
        Bitmap input = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(input, 0, 0, paint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        output.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.setWidthPercentage(80);
        return image;
    }

    private static Image getBarcodeImage(PdfWriter pdfWriter, String barcodeText) {
        Barcode128 barcode = new Barcode128();
        //barcode.setBaseline(-1); //BARCODE TEXT ABOVE
        barcode.setFont(null);
        barcode.setCode(barcodeText);
        barcode.setCodeType(Barcode128.CODE128);
        barcode.setTextAlignment(Element.ALIGN_BASELINE);
        return barcode.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, null);
    }

    public static Image getImageFromDrawable(BitmapDrawable bitmapDrawable) {
        if (bitmapDrawable != null) {
            Bitmap bmp = bitmapDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            try {
                Image image = Image.getInstance(stream.toByteArray());
                image.setAlignment(Element.ALIGN_RIGHT);
                image.scaleToFit(40, 40);
                return image;
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static Bitmap getImagesFromPDF(File pdfFilePath, Context context) throws IOException {
        File destinationFolder = new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES);
//        File destinationFolder = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(pdfFilePath, ParcelFileDescriptor.MODE_READ_ONLY);
        PdfRenderer renderer = new PdfRenderer(fileDescriptor);
        final int pageCount = renderer.getPageCount();
        for (int i = 0; i < pageCount; i++) {
            PdfRenderer.Page page = renderer.openPage(i);
            Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            page.close();
            String timeStamp = (new SimpleDateFormat(context.getString(R.string.save_format_name))).format(new Date());
            String fileNameToSave = "JPEG_" + new Random().nextInt() + "_" + timeStamp + ".jpg";
            File file = new File(destinationFolder.getAbsolutePath(), fileNameToSave);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
