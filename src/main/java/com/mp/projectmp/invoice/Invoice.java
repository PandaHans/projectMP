package com.mp.projectmp.invoice;

import com.mp.projectmp.base.Client;
import com.mp.projectmp.base.Project;
import com.mp.projectmp.helper.UserInputHelper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

//Template Method Pattern
public abstract class Invoice {
    UserInputHelper userInputHelper = new UserInputHelper();

    final public void createInvoicePDF(Client client, Project project) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 750);

        try {
            if (wantsClient()) {
                addClient(contentStream, client);
            }
            if (wantsProject()) {
                addProject(contentStream, project);
            }
            if (wantsDagen()) {
                int maand = userInputHelper.getMaand();
                int jaar = userInputHelper.getJaar();
                addDagen(contentStream, client, project, maand, jaar);
            }
            contentStream.endText();
        } finally {
            contentStream.close();
            document.save("invoice.pdf");
            document.close();
        }

        System.out.println("PDF gemaakt");
    }


    abstract void addDagen(PDPageContentStream contentStream, Client client,Project project, int maand, int jaar) throws IOException;
    abstract void addClient(PDPageContentStream contentStream, Client client) throws IOException;
    abstract void addProject(PDPageContentStream contentStream, Project project) throws IOException;

    //hooks overwrite als je bv geen dagen wilt
    boolean wantsDagen() {
        return true;
    }
    boolean wantsClient() {
        return true;
    }
    boolean wantsProject() {
        return true;
    }


}