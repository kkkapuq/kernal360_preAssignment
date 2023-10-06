package controller;

import entity.Career;
import entity.Education;
import entity.UserVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import view.ResumeView;

import java.io.*;

/**
 * 이력서 생성 및 저장
 */
public class ResumeController {
    static ResumeView rv = new ResumeView();
    static Sheet sheet;
    static Workbook workbook;
    static UserVO userVO;
    static int ROWNUM = 0;

    // 이력서 생성
    public static void createResume() throws FileNotFoundException {
        userVO = new UserVO();
        userVO.setPersonInfo(rv.inputPersonalInfo());
        userVO.setEducationList(rv.inputEducationList());
        userVO.setCareerList(rv.inputCareerList());
        userVO.setSelfIntroduction(rv.inputSelfIntroduction());
    }

    // 이력서 시트 생성
    public static void createResumeSheet() throws IOException {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("이력서");
        sheet = createPersonInfo(sheet);
        sheet = createEduInfo(sheet);
        sheet = createCareerInfo(sheet);
    }

    // 기본 정보 생성
    public static Sheet createPersonInfo(Sheet sheet) throws IOException {
        Row headerRow = sheet.createRow(ROWNUM++);
        headerRow.createCell(0).setCellValue("사진");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("이메일");
        headerRow.createCell(3).setCellValue("주소");
        headerRow.createCell(4).setCellValue("전화번호");
        headerRow.createCell(5).setCellValue("생년월일");

        // 너비 조절
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 4000);

        Row personInfoRow = sheet.createRow(ROWNUM++);
        personInfoRow.setHeightInPoints(50);
        inputPhoto();

        personInfoRow.createCell(1).setCellValue(userVO.getPersonInfo().getName());
        personInfoRow.createCell(2).setCellValue(userVO.getPersonInfo().getEmail());
        personInfoRow.createCell(3).setCellValue(userVO.getPersonInfo().getAddress());
        personInfoRow.createCell(4).setCellValue(userVO.getPersonInfo().getPhoneNumber());
        personInfoRow.createCell(5).setCellValue(userVO.getPersonInfo().getDate());

        return sheet;
    }

    // 학력 정보 생성
    public static Sheet createEduInfo(Sheet sheet) {
        Row headerRow = sheet.createRow(ROWNUM++);
        headerRow.createCell(0).setCellValue("졸업년도");
        headerRow.createCell(1).setCellValue("학교명");
        headerRow.createCell(2).setCellValue("전공");
        headerRow.createCell(3).setCellValue("졸업여부");

        for (int i = 0; i < userVO.getEducationList().size(); i++) {
            Education edu = userVO.getEducationList().get(i);

            Row eduInfoRow = sheet.createRow(ROWNUM++);
            eduInfoRow.createCell(0).setCellValue(edu.getGraduationYear());
            eduInfoRow.createCell(1).setCellValue(edu.getSchoolName());
            eduInfoRow.createCell(2).setCellValue(edu.getMajor());
            eduInfoRow.createCell(3).setCellValue(edu.getGraduationStatus());
        }
        return sheet;
    }

    // 경력 정보 생성
    public static Sheet createCareerInfo(Sheet sheet) {
        Row headerRow = sheet.createRow(ROWNUM++);
        headerRow.createCell(0).setCellValue("근무기간");
        headerRow.createCell(1).setCellValue("근무처");
        headerRow.createCell(2).setCellValue("담당업무");
        headerRow.createCell(3).setCellValue("근속연수");

        for (int i = 0; i < userVO.getCareerList().size(); i++) {
            Career career = userVO.getCareerList().get(i);

            Row eduInfoRow = sheet.createRow(ROWNUM++);
            eduInfoRow.createCell(0).setCellValue(career.getWorkPeriod());
            eduInfoRow.createCell(1).setCellValue(career.getCompanyName());
            eduInfoRow.createCell(2).setCellValue(career.getJobTitle());
            eduInfoRow.createCell(3).setCellValue(career.getEmploymentYears());
        }
        return sheet;
    }

    // 이미지 삽입
    public static void inputPhoto() throws IOException {
        InputStream imageStream = new FileInputStream(userVO.getPersonInfo().getPhoto());
        byte[] bytes = IOUtils.toByteArray(imageStream);

        int pictureIdx = workbook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_PNG);
        imageStream.close();

        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();

        // 이미지를 삽입할 셀의 위치 설정
        anchor.setCol1(0); // 열 (0부터 시작)
        anchor.setRow1(1); // 행 (0부터 시작)
        anchor.setCol2(1);
        anchor.setRow2(2);

        Picture picture = drawing.createPicture(anchor, pictureIdx);

        picture.resize(1.0, 0.7); // 기본 크기
    }

    // 자기소개 시트 생성
    public static void createSelfIntroductionSheet() {
        sheet = workbook.createSheet("자기소개서");

        Row headerRow = sheet.createRow(0);

        sheet.setColumnWidth(0, 20000);
        headerRow.setHeightInPoints(300);
        headerRow.createCell(0).setCellValue(userVO.getSelfIntroduction());
    }

    // 이력서 저장
    public static void saveWorkbookToFile() throws IOException {
        // 엑셀 파일 저장
        FileOutputStream outputStream = new FileOutputStream("이력서.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        createResume();
        createResumeSheet();
        createSelfIntroductionSheet();
        saveWorkbookToFile();

    }
}
