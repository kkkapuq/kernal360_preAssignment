package view;

import entity.Career;
import entity.Education;
import entity.PersonInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 사용자로부터 이력서 정보를 입력
 */
public class ResumeView {
    private Scanner sc = new Scanner(System.in);

    // 개인정보 입력
    public PersonInfo inputPersonalInfo() throws FileNotFoundException {
        PersonInfo personInfo = new PersonInfo();
        while (true) {
            System.out.printf("사진 파일명을 입력하세요: ");
            String inputPath = sc.nextLine();
            if (!checkPersonalInfo("photo", inputPath)) {
                System.out.println("유효한 경로가 아닙니다. 다시 입력해주세요.");
                continue;
            }
            personInfo.setPhoto(inputPath);
            break;
        }
        System.out.println("이름을 입력하세요: ");
        personInfo.setName(sc.nextLine());
        System.out.println("이메일을 입력하세요: ");
        personInfo.setEmail(sc.nextLine());
        System.out.println("주소를 입력하세요: ");
        personInfo.setAddress(sc.nextLine());
        System.out.println("전화번호를 입력하세요: ");
        personInfo.setPhoneNumber(sc.nextLine());
        while (true) {
            System.out.println("생년월일을 입력하세요 (예 : 1990-01-01): ");
            String date = sc.nextLine();
            if (!checkPersonalInfo("date", date)) {
                System.out.println("유효한 날짜가 아닙니다. 다시 입력해주세요.");
                continue;
            }
            personInfo.setDate(date);
            break;
        }
        return personInfo;
    }

    // 입력값 유효성 검증
    private boolean checkPersonalInfo(String key, String value) {
        // 사진
        if (key.equals("photo")) {
            File imageFile = new File(value);
            if (imageFile.exists() && !imageFile.isDirectory()) {
                return true;
            } else {
                return false;
            }
        } else {
            // 생년월일이 유효한지 확인
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(value, dateFormatter);
            return isValidDate(date) ? true : false;
        }
    }

    // 날짜 유효성 검사 함수
    public static boolean isValidDate(LocalDate date) {
        try {
            // LocalDate 객체를 생성할 때 예외가 발생하지 않으면 유효한 날짜
            LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
            return true;
        } catch (java.time.DateTimeException e) {
            return false;
        }
    }

    // 학력정보 입력
    public List<Education> inputEducationList() {
        List<Education> list = new ArrayList<>();
        while(true){
            System.out.println("학력 정보를 입력하세요 (종료는 q): ");
            System.out.println("졸업년도 학교명 전공 졸업여부");
            String[] eduInfo = sc.nextLine().split(" ");
            if (eduInfo.length == 1 && eduInfo[0].equals("q")) {
                break;
            }
            if(eduInfo.length != 4 || eduInfo[0].length() != 4){
                System.out.println("입력 형식이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
            list.add(new Education(eduInfo[0], eduInfo[1], eduInfo[2], eduInfo[3]));
        }

        return list;
    }

    // 경력정보 입력
    public List<Career> inputCareerList() {
        List<Career> list = new ArrayList<>();
        while(true){
            System.out.println("경력 정보를 입력하세요 (종료는 q): ");
            System.out.println("근무기간 근무처 담당업무 근속연수");
            String[] careerInfo = sc.nextLine().split(" ");
            if (careerInfo.length == 1 && careerInfo[0].equals("q")) {
                break;
            }
            if(careerInfo.length != 4 || careerInfo[0].length() != 21){
                System.out.println("입력 형식이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
            String[] temp = careerInfo[0].split("~");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            if (!isValidDate(LocalDate.parse(temp[0], dateFormatter)) || !isValidDate(LocalDate.parse(temp[1], dateFormatter))) {
                System.out.println("날짜 입력 형식이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
            list.add(new Career(careerInfo[0], careerInfo[1], careerInfo[2], careerInfo[3]));
        }

        return list;
    }

    // 자소서 입력
    public String inputSelfIntroduction() {
        System.out.println("자기소개서를 입력하세요. 여러 줄을 입력하려면 빈 줄을 입력하세요.");
        StringBuilder sb = new StringBuilder();
        while (true) {
            String temp = sc.nextLine();
            if(temp.equals(""))
                break;
            sb.append(temp + "\n");
        }
        System.out.println("이력서 생성이 완료되었습니다.");
        return sb.toString();
    }
}
