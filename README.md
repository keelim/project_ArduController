## project Arducon project

## project manager: keelim
## start date: March 1
## complete date: June 30  

## 2019 03 29
1. sample 코드 
2. 코드 분석
3. controller layout 구현 --> 액티비티 생성
4. ~~firebase 연결을 할 것~~
5. ~~firebase basic test~~
6. adMob 은 일단 미뤄두기

## 2019 03 30
1. 안드로이드 레이아웃 작성 
2. 아이콘 만들기

## 2019 04 02
1. 안드로이드 아이콘 만들기
2. Fragment를 확인을 할 수 는 있지만 있지만 일단은 보류
3. 액티비티 3개는 확실히 한다. --> 일단 xml 과정에서 진행을 하는 것이다.
    - 액티비티 전환 과정 main, bluetooth setting, 아두이노 컨트롤러
4. 블루투스 통신 모듈 준비 --> 아두이노.ino 파일 예제 작성
5. firebase test
  
## 2019 04 05
1. controller xml 수정
2. appBar 오버플로우 아이템 생성 --> 앱바의 사용은 무조건 오버플로우 액션으로
3. 화면 간의 전환 액티비티를 사용
4. manifest의 액티비티를 등록을 해야 작동을 한다. 
5. 수정 사항 controller landscape 로 변경 할 것

## 2019 04 08
1. UUID ? 
    - 간단한 유니크한 아이디
    - 내부 저장소 에서 관리를 해야 한다.  
    <code> String UUID = UUID.randomUUID().toString() </code>
    
2. Handler 조정 방법
3. StringBuilder
    - String을 생성을 하여 두개를 합치는 것이 생각보다 비효율 적이다. 
    - 문자열을 더할 때 객체를 생성하는 것이 아니라 기존의 데이터에서 더한다.
    - 속도가 빠르다. 상대적 부하가 적다.
    - 긴 문자열은 StringBuilder 가 효율적
    <code>
    Stringbuilder stringbuilder = new StringBuilder();
    stringbuilder.append("ABC");
    stringbuilder.append("DEF");
    stringbuilder.toString(); //string 으로 변환을 시킨다. 
    </code>
    
4. 블루투스 작동 구조
5. Setting Activity  toolbar  --> action bar 대체를 할 것

## 2019 04 09
1. 드로우 레이아웃 액션바에서 구현을 할 것
    - 드로우 레이아웃을 액션바에 넣고
    - 액션바에서 아이콘을 클리을 하는 경우 --> 드로우 레이아웃이 나오는 구조가 좋을 듯 하다. 

## 2019 04 13 
1. 드로우 레이아웃 일단 제거를 할 것

## 2019 04 14
1. 앱바 --> 액션바, 툴바 2가지로 구성을 할 수 있다. 
    - Actionbar old version,  Toolbar latest version
    - Toolbar > Actionbar
    - 툴바를 잡아주고
    - menu.xml를 작성
    - 툴바를 이용하여 setSupportActionbar() 를 통하여 잡아주고 --> code 적 이용방법 
    - 액션바의 설정을 이용을 한다. 
    
2. 드로어 레이아웃 설정
    - 드로어 레이아웃의 설정의 버튼 
    - R.id.home 으로 표현을 한다.
    <code>drawerLayout.openDrawer(GravityCompat.START);</code>
    <code>drawerLayout.closeDrawers()</code>
    드로어 레이아웃을 닫아준다. 
     
3. Toast 메시지 보다는 SnackBar 깔끔할 수 도 있을 것이라는 생각을 해본다.
    <code>SnackBar.make(Toolbae, "", Snackbar.SHORT").show</code>

## 2019 04 15
1. temp drawer layout 
2. ready to the other setting
    - 아이콘 생성 준비
    - 블루투스 코드 수정
    - 레이아웃 기능 최소화
    - 드로어 레이아웃 정리
    - component 확인
    - flow chart
    x
3. Test adMob setting 
    - 출시 시 adMob id 변경을 할 것
    - xml도 변경을 해야지
    - code, @string/addMobid 
    
## 2019 04 16
1. drawer layout  --> 슬라이드 메뉴?
    - drawer 레이아웃으로 적용 --> 드로어 레이아웃 정상 작동 확인 
    - 드로어 레이아웃 수정을 할 것
    - 드로어 레이아웃 수정 완료 --> 작동 확인
    - R.id.home 사용을 하는 방법
     <code> <include layout="@layout/apply_darwer" /> </code>
2. 전체적인 레이아웃 수정 준비 --> 하나의 액티비티 수정을 할 필요가 있나?
    
3. 드로어 레이아웃 홈버튼 활성화 완료
    - android.R.id.home 으로 접근을 해야 할 수 있다는것을 기억을 할 것
        
## 2019 04 17
1. 아이콘 생성 준비
2. 블루 투스 코드 수정
3. drawerView 이벤트 리스터 작동을 할 것
4. component 확인

## 2019 04 18
1. 아이콘 생성
    - 딱히 생성을 할 게 있나?
    - navigation view 는 설정을 하는 것이 좋을 것 같다.

## 2019 04 20
1. 개발 완료
2. 지속적인 업데이트 진행
3. 버전 코드 수정
    - app --> gradle version name and version
    - BuildConfig 수정 안해도 된다. 
4. 프로그램 개발 계획
    - 블루 투스 코드 수정
    - 충돌 볼고서 (Crashtyics의 상태 확인)
    - 다른 컴포넌트 사용성 개선
    - 색깔 전부 수정 --> 파란색으로
    - 설정 기능 먼저 준비
    - 릴리즈 노트 작성 
    - developer console 수정
    - 액티비티 기능들 준비 할 것
5. gradle 3.4.0 error
    - 이유를 모르겠다. 호환이 안되는 건가?
6. 매주 목요일 업데이트
7. drawer view snackbar.
    - fragment로 작동을 시키기?
    - implements OnNavigationItemSelectedListener
8. Toggle?
    - Boolean switch?
    - Button?
    
## 2019 04 21
1. 블루투스 코드 1차 수정
    - 임의로 finish 하는 부분 제거
    - Buffer 부분은 수정 필요
2. 설정 부분은 새로 만들 거나 fragment 부분으로 사용하는 것이 좋을 듯
3. 안드로이드 인트로 화면 개발
    - 1초 정도만 보여주고 종료 시키는 것
    -  xml의 구성을 완료
4. what is the handler?
    - 객체들의 메시지를 처리하는 객체
    - 다른 쓰레드는 접근을 할 수 가 없다. 
    - handler를 통하여 쓰레드의 접근을 함으로써 뷰들의 갱신을 스레드가 요청
    
## 2019 04 22
1. 블루투스 코드 2차 수정
    - 블루투스 코드 간소화
    - 오류가 있는 부분은 수정을 할 것
2. 내부 UI 수정
    - 새로운 액티비티 추가를 할 것
3. 내부 동작 수정
    - 액티비티 하이라키 준비
4. 기능 
    - 블루투스 테스트 기능 준비?
        - 아두이노와 블루투스가 통신이 되는지를 체크 하는 기능
        - like ping()
    - 블루 투스 코드 수정
    - 충돌 보고서 (Crashtyics의 상태 확인)
    - 다른 컴포넌트 사용성 개선
    - 색깔 전부 수정 --> 파란색으로
    - 설정 기능 먼저 준비
    - 릴리즈 노트 작성 
    - developer console 수정
    - 액티비티 기능들 준비 할 것
    
## 2019 04 23
<code>overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);</code>
1. 애니메아션의 사용
    - PendingTransition
2. 기본적으로 개발에서 어떤 것을 사용을 하는가?
3. fragment 사용?
    - setting
    - Preference fragment 설정
4. 1.3 version release

## 2019 04 24
1. setting fragment 오류 개선 필요
    - 일단 코드 수정
2. 더 추가적으로 해야 하는 상황
    - 블루투스 코드 수정 
    - adMob 안나오는 것 수정 unitID 가 따로 존재
    - setting_preference 사용법 익힐 것
    - UI, UX 개선 필요
    - fragment의 활용
    - 액티비티의 추가
    - logger --> 
    - 기능은 2가지
        - 블루투스를 통한 통신
        - logging 기능
3. 난독화 proguard 를 통하여 사용을 할 수 없게 만든다. 
    - 활용을 할 수 있는 방안?

## 2019 04 25
1. firebase console check
2. google play console
3. design pattern check?
    - logic --> business logic
    - present --> View present 
    - data --> database get data
4. preference 재설정
    - fragment와 액티비티를 연결을 하여 사용을 한다. 
    - fragment는 하나의 레이아웃 파일을 포함해야 한다.
    - 액티비티와 레이아웃을 연결을 한다. 
    - 액티비티의 전환
    - 제트팩은 코틀린을 만져야 사용을 할 수 있다. 
    - ? 코틀린을 배워야 한다.?
    
## 2019 04 27 
1. reset firebase provide error
2. navigation drawer itemselected listener
3. notification channel 다시 구현을 할 것
    - 알림 채널 앱 구현 중 연속 실행을 할 것
4. 블루투스 이상 유무 체크를 할 것
    -  HC-06 bluetooth test
    
## 2019 04 29
1. menu toolbar color change

## 2019 04 30
1. notification channel use
    - 앱 실행중 계속 알림 창에 나타 날 것
2. 버그 리포트 깃허브 페이지
    - arducon release note 항목 혹은 버그 리포트 항목
    - 딥링크?
3. 계정 항목은 카카오톡이나 지메일로 한정
    - 최대한 여러가지 기술을 사용을 해보도록 하자.
    - fragment --> back 키를 어떻게 구현을 할 것인가?
    -  일단 작동을 하는 것 같다. back 키를 누르면 다시 drawerLayout 이 나오는 부분
    
4. 뒤로가기 버튼을 누르면 액티비티가 종료를 하는 것이 아니라 fragment가 없어지게 만들기
    - 액티비티에서 인터페이스를 정의를 하고 사용을 한다.
     
## 2019 05 01
- using the AndroidX library 
1. 블루투스 체크 해봤는데 데이터를 받긴 한다. 
2. SharedPreference 재설정을 할 것
    - 파일로써 저장
    - 설정 페이지의 어울리는 것 같다. 
    -  초기 사용자 용으로도 사용을 할 수 도 있을 것 같다. 
    -  초기 사용자용 액티비티 구현? 

## 2019 05 07
1. 키 오류로 인한 오류
    -  키관리는 항상 잘 할 것
2. WebView 기본 setting
    - 최적화 작업을 할 수 있는가?
    - 크롬으로만 사용
    - 기본 브라우저? 

## 2019 05 09 
1. 긴급 오류 대응
    - 갑자기 종료되는 현상 대처
    - 액티비티 메니페스트 미등록?
    
## 2019 05 10
1. 웹뷰 setting 
    - 클릭 시 일반 인터넷 앱으로 넘어가는 문제 수정
    - 다른 설정 방법은 있는가? --> 자바 스크립트 허용은 보안 문제
    - 특별히 사용을 할 필요는 없다.
2. 설정 페이지를 다시 할 필요가 있다.
    - SharedPreference 설정
    - 액티비티를 새로 사용을 해야 하는가?
    - SettingActivity 사용
3. 안드로이드 In_app update 
    - 어떻게 처리를 해야 하는 거야?

## 2019 05 15
1. 크래쉬틱스 상태가 별로 안좋다.
    - 무슨 오류인지 파악도 안된다.
    - 일단 커밋은 남겨서 되돌릴 수는 있다.
    - 
2. version 1.7 version 8 update

## 2019 05 16 
1. D8 warnings 가 왜 생기는지 이해가 안간다.? 
    - Gradle 의 dependency 문제인 것 같은데 
2. 충돌 보고서도 일단 작동을 하지 않는 것 같다.  
    - Gradle의 문제인 것 같기는 하다.
3. Clean code 과정 수행 완료
4. version 1.8 update 

## 2019 05 16 
1. 긴급 패치 필요할 것 갑자기 종료되는 문제 발생
2. 긴급 점검 완료
    - 로그인 과정에서의 오류
    - 우선 액티비티에서의 제거
3. 버전 네이밍 변경 2.0.0
4. 버그 리포트 작성 --> 사실상 개발일지

## 2019 05 17
1. version naming change 1.8 --> 2.0.0
2. writing the bug report 2.0.0

## 2019 05 19
1. 안드로이드 컴포넌트 및 블루투스 코드
2. 버그 리포트 준비 2.0.1
3. junit 및 안드로이드 에스프레소 작성 
    - 활용법을 아직 모른다. 
    - 어느 방식으로 작동을 하는가?
    
## 2019 05 20
1. gradle setting glide

## 2019 05 23
1. 버그 리포트 작성
2. 기타 추가 기능들 확정을 할 것 --> 일단 firebase auth 나 crashtytics 를 넣으면 오류가 나는 것은 확인

# 2019 06 15
1. Crashtlytics setting complete

## 2019 06 16
1. Linking firebase app 

## 2019 06 17
1. ready to other new feature
    - firebase setting is complete
    - Authentication 

## 2019 06 19
1. developer page making 
2. business logic plan making
3. other feature

## 2019 06 20
1. firebase version 2.0.4 release
2. code version change 2.0.5
3. FCM main target

## 2019 06 26
1. firebase clouding message (fcm) 만들어 보기

## 2019 06 27
1. Firebase Clouding Messaging ~ing
2. deBugging drawer account
3. version 2.0.5 release

## 2019 07 03
1. plugins setting
2. resource setting

## 2019 07 04
1. dark mode setting
    - theme setting

## 2019 07 05
1. developer page making complete

# Todo
- gradle setting optimize
- auth feature 
- database setting // serverside?


    
    
