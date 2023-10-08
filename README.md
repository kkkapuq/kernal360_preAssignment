- [이력서 자동생성 프로그램](#-------------)
- [클래스 계층도 및 동작 흐름](#---------------)
- [구현 기능](#-----)
    * [ResumeView](#resumeview)
        + [`inputPersonalInfo()`](#-inputpersonalinfo---)
        + [`checkPersonalInfo()`](#-checkpersonalinfo---)
        + [`isValidDate()`](#-isvaliddate---)
        + [`inputEducationList()`](#-inputeducationlist---)
        + [`inputCareerList()`](#-inputcareerlist---)
        + [`inputSelfIntroduction()`](#-inputselfintroduction---)
    * [ResumeController](#resumecontroller)
        + [`createResume()`](#-createresume---)
        + [`createResumeSheet()`](#-createresumesheet---)
        + [`createPersonInfo()`](#-createpersoninfo---)
        + [`createEduInfo()`](#-createeduinfo---)
        + [`createCareerInfo()`](#-createcareerinfo---)
        + [`inputPhoto()`](#-inputphoto---)
        + [`createSelfIntroductionSheet()`](#-createselfintroductionsheet---)
        + [`saveWorkbookToFile()`](#-saveworkbooktofile---)
- [입출력 내용](#------)
    * [입력](#--)
    * [출력](#--)
        + [이력서 페이지](#-------)
        + [자기소개서 페이지](#---------)
- [개발 도구](#-----)
- [요건 외 추가 구현 사항](#-------------)
    * [정상적인 날짜 판단](#----------)
    * [입력된 사진 파일의 유효 여부](#----------------)
    * [학력정보, 경력정보를 입력받을 때 시작도연 기준으로 내림차순 정렬처리](#--------------------------------------)
    * [UserVO를 생성해 지원자 정보 통합](#uservo---------------)

# 이력서 자동생성 프로그램
> 이력서에 해당하는 내용들을 콘솔로 입력받아 해당 내용들을 바탕으로 엑셀파일을 생성해주는 프로그램입니다.
>

# 클래스 계층도 및 동작 흐름

![클래스계층도.png](https://file.notion.so/f/f/5a188a69-a344-47f8-ae6e-41eb74a81e16/326f8a6a-5961-476e-b889-7fe55eeaa1d7/%ED%81%B4%EB%9E%98%EC%8A%A4%EA%B3%84%EC%B8%B5%EB%8F%84.png?id=c5e63fa8-fc27-45b4-b08d-1d7b8d502d29&table=block&spaceId=5a188a69-a344-47f8-ae6e-41eb74a81e16&expirationTimestamp=1696860000000&signature=ZUSQJLziMFzsaV1xS25ppKRALnXC7TbUBKovkW0rknM&downloadName=%ED%81%B4%EB%9E%98%EC%8A%A4%EA%B3%84%EC%B8%B5%EB%8F%84.png)

1. **경력정보**를 가지고있는 클래스 `Career`
   **학력정보**를 가지고있는 클래스 `Education`
   **개인정보**를 가지고있는 클래스 `PersonInfo`
   이러한 정보를 포함하고 있는 **통합 객체**인 `UserVO`로 관리되게 됩니다.
2. **`ResumeView`**에 작성된 메서드를 **`ResumeController`**에서 호출합니다.
3. **`ResumeController`**에서 호출되고 작성된 내용을 바탕으로, **이력서.xlsx** 라는 파일을 생성합니다.

# 구현 기능

## ResumeView

> 사용자로부터 콘솔로 데이터를 입력받는 `view`입니다.
>

### `inputPersonalInfo()`

개인정보를 입력받습니다.

**사진, 이름, 이메일, 주소, 전화번호, 생일**을 입력받습니다.

### `checkPersonalInfo()`

개인정보를 입력받을 때 사용되는 유효성 검증 메서드입니다.

사진 파일의 경로 및 이름이 유효한지, 생년월일이 유효한 날짜인지를 판별합니다. 정상적이지 않다면 재입력하도록 안내합니다.

### `isValidDate()`

날짜의 유효성을 검사합니다.

`DateTimeFormatter, LocalDate`를 활용합니다.

### `inputEducationList()`

학력정보를 입력받습니다.

**졸업년도, 학교, 전공, 졸업여부**를 공백을 기준으로 입력받습니다.

### `inputCareerList()`

경력정보를 입력받습니다.

**근무기간, 근무처, 담당업무, 근속연수**를 공백을 기준으로 입력받습니다.

### `inputSelfIntroduction()`

자기소개서를 입력받습니다.

엔터키가 눌릴때마다 개행(\n) 처리되며, 공백 입력 시 종료 및 이력서 생성이 완료되었다는 문구를 노출합니다.

## ResumeController

> main함수 및 `view`를 호출해 데이터를 입력받고, 이력서 생성 및 엑셀파일 저장이 이루어지는 `controller`입니다.
>

### `createResume()`

view를 호출해 이력서 생성에 필요한 데이터를 모두 입력받습니다.

개인정보 - 학력정보 - 경력정보 - 자기소개서 순으로 호출됩니다.

### `createResumeSheet()`

이력서 시트를 생성하고, 해당 시트에 데이터를 삽입하는 메서드입니다.

### `createPersonInfo()`

이력서 시트 내 기본정보 데이터를 생성해주는 메서드입니다.

### `createEduInfo()`

이력서 시트 내 학력정보 데이터를 생성해주는 메서드입니다.

### `createCareerInfo()`

이력서 시트 내 경력정보 데이터를 생성해주는 메서드입니다.

### `inputPhoto()`

사진(이미지)를 생성해주는 메서드입니다.

### `createSelfIntroductionSheet()`

자기소개 시트를 생성하고, 해당 시트에 자기소개서 내용을 생성하는 메서드입니다.

### `saveWorkbookToFile()`

생성된 이력서를 기반으로 엑셀 파일로 출력하는 메서드입니다.

# 입출력 내용

## 입력

```
이력서사진.png
패캠맨
fastcampusman@fastcampus.co.kr
서울특별시
010-1111-1111
1990-01-04
2015 패캠대 컴퓨터공학 졸업
2019 패캠대학원 인공지능 졸업
q
2018.01.01~2020.01.02 패스트캠퍼스 개발 2년
2021.01.01~2019.01.02 패스트파이브 운영 1년
q
자기소개(성장배경)
어렸을 때부터 강인하신 아머지와 자비로우신 어머니 어쩌구
성격의 장단점
긍정적이고 밝은 성격인데 가끔은 외로운 어쩌구
```

## 출력

### 이력서 페이지

![Untitled](https://file.notion.so/f/f/5a188a69-a344-47f8-ae6e-41eb74a81e16/4a7a5c9d-be9d-4d88-9e46-7d4cad276b31/Untitled.png?id=ef1debe4-2b20-4353-a007-4448581bc980&table=block&spaceId=5a188a69-a344-47f8-ae6e-41eb74a81e16&expirationTimestamp=1696860000000&signature=vzU7PKEtScK6yuHIOT4iGgq-7DscfwEW08QsGT7Cz8Q&downloadName=Untitled.png)

### 자기소개서 페이지

![Untitled](https://file.notion.so/f/f/5a188a69-a344-47f8-ae6e-41eb74a81e16/1aebfb2e-8e73-42af-9096-4f1cc1b193f4/Untitled.png?id=f4af3b4a-7cbe-4021-94f8-39a0ce19e596&table=block&spaceId=5a188a69-a344-47f8-ae6e-41eb74a81e16&expirationTimestamp=1696860000000&signature=K-I6u_fN2SI5JvuU1zH0bRPrH6OFi1CYOE2gq8wLQDU&downloadName=Untitled.png)

# 개발 도구

Java 17, poi, IntelliJ, git, sourcetree

# 요건 외 추가 구현 사항

최소한의 유효성 검증은 있어야 완성도가 높아질 것이라고 판단되었습니다.

지엽적인 부분은 최소화하고, 커밋컨벤션 30줄에 최대한 맞춰 가능한 선에서 유효성 검증 로직을 추가했습니다.

## 정상적인 날짜 판단

`isValidDate()`메서드에 사용된 내용입니다. `LocalDate`를 활용해 날짜 객체로 변환시킨 다음, 생년월일, 근무년도를 입력할때 사용됩니다.

졸업연도는 연도만 입력하고, 허용되는 range가 넓기 때문에 제외했습니다.

```java
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
```

## 입력된 사진 파일의 유효 여부

`checkPersonalInfo()`메서드에 사용된 로직입니다.

매개변수에 key, value가 있는데, 입력받을 때 생년월일에도 동일하게 해당 메서드를 사용하기 위함입니다.

`File` 클래스의 `isDirectory()` 및 `exists()` 메서드를 활용해 파일의 존재 여부를 판별합니다.

```java
// 사진
if (key.equals("photo")) {
    File imageFile = new File(value);
    if (imageFile.exists() && !imageFile.isDirectory()) {
        return true;
    } else {
        return false;
    }
}
```

## 학력정보, 경력정보를 입력받을 때 시작도연 기준으로 내림차순 정렬처리

경험 상, 대부분 지원서는 학력정보와 경력정보를 입력할 때 최신 정보가 위로 노출되도록 설계되어있었습니다.

해당 기능을 구현하고자, `Comparator` 인터페이스를 구현해 시작일자 기준으로 내림차순 처리되도록 로직을 작성했습니다.

경력정보는 날짜를 입력받기 때문에 `LocalDate`를 활용했고, 졸업연도는 숫자만 입력받기 때문에 별도의 날짜 객체 처리없이 int화 시켜서 정렬되도록 처리했습니다.

```java
// 경력정보 정렬
Collections.sort(list, new Comparator<Career>() {
    @Override
    public int compare(Career o1, Career o2) {
        // 근무기간을 날짜로 파싱하여 비교
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate date1 = LocalDate.parse(o1.getWorkPeriod().split("~")[0], dateFormatter);
        LocalDate date2 = LocalDate.parse(o2.getWorkPeriod().split("~")[0], dateFormatter);

        // 내림차순 정렬
        return date2.compareTo(date1);
    }
});

// 학력정보 정렬
Collections.sort(list, new Comparator<Education>() {
    @Override
    public int compare(Education o1, Education o2) {
        int date1 = Integer.parseInt(o1.getGraduationYear());
        int date2 = Integer.parseInt(o2.getGraduationYear());

        // 내림차순 정렬
        return date2 - date1;
    }
});
```

## UserVO를 생성해 지원자 정보 통합

구현은 Career, Education, PersonInfo 만으로도 충분합니다.
하지만 논리적으로 미루어보았을 때, 한 사람이 가지고 있는 정보들에 해당합니다.

따라서, 이들을 가지고 있는 통합객체인 `UserVO`를 생성 및 관리하였고, 추후 이력서 생성 시에도 해당 객체를 통해 접근하면 되기에, 유지보수 관리가 용이하다고 판단되어 이처럼 설계했습니다.