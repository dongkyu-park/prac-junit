package me.kyu.pracjunit;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 메서드, 클래스 모두 명시 가능. 클래스에 명시 하면, 클래스 내 모든 메서드의 displayName이 설정됨
class StudyTest {

    @Test
    @DisplayName("스터디 만들기 😀")
    void create_new_study() {

        Study study = new Study(10);

        assertAll(
                () -> assertNotNull(study),
//              assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
                        @Override
                        public String get() {
                            return "스터디를 처음 만들면 DRAFT 상태다.";
                        }
                        }),
                // "" String 형태로 바로 적어두면 test가 실패하든 성공하든 연산 작업을 무조건 한다.
                // 예를 들어, "안녕" + studyStatus.DRAFT + "하세요" 등으로 문자열이 복잡해진다면, 리소스가 많이 소모 될 것이다.
                // 해당 형태로 람다식의 형태로 만들어두면, 최소한의 필요한 부분에서만 연산작업이 이루어지게 된다.
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );

    }

    @Test
    @DisplayName("스터디 만들기2 😀")
    void create_new_study2() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

    }

    @Test
    @DisplayName("스터디 만들기3 😀")
    void create_new_study3() {

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // assertTimeout의 경우, 비교하는 코드 블럭이 모두 실행될 때까지 대기한다.

        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // assertTimeoutPreemptively의 경우, 100밀리세컨이 지나면 바로 테스트를 실패하게 한다.

    }

    @Test
    @DisplayName("스터디 만들기 (ง •_•)ง")
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}