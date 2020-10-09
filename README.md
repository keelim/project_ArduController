# AOSP TESTING APPLICATION

made by keelim (JAEHYUN KIM)

## 테스트 만들기
1. Alert DIalog 를 활용을 하여 성능 측정을 할 것
2. 파일은 csv 파일을 만들고 어디에 넣어야 하는가?
3. handle 메시지는 찾아보기


## addWindow test 10000 어플리케이션 구성 완료
1. 10000 이상 메모리 부족으로 데이터 저장 안됨
2. 내부 storage 데이터 값을 저장하고 -> firebase storage 저장
3. 데이터 값 확인 완료 HashMap version 성능 좋은 것 확인

## Handler Message 구현 확인 하기
1. Handler 어떻게 써야 하는지 확인을 할 필요가 있다.
2. PhoneWindowManager -> PolicyHandler 에서 어느 부분을 콜을 해야 하는지 확인할 필요가 있다.
3. firebase storage 코드는 잘 간직을 할 것  
4. window policy 를 만지는 코드라 성능 측정이 어렵다. -> 호출 코드를 찾아보니 안드로이드 맨 위 상단에 상태바
recentApps() 함수를 호출을 하는데 성능 측정이 무리가 있지 않을까?

