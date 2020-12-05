# 블루투스 통신 컨트롤러

## IoT 보드를 활용하여 블루투스 통신을 하는 어플리케이션
IoT 보드 저전력 블루투스 모듈을 사용하고 테스트하기 위하여 만든 어플리케이션

`블루투스` : UHF 단파 통신 (2.4 mHz) 을 사용해서 낮은 속도로 디지털 정보를 통신하는 방법
`저전력 블루투스`: 블루투스 통신에서 duty cycle 을 밀리초로 구현하여 가벼운 구동 방식을 취하여 대부분 슬립 모드로 있어 전력 소모가 적음

##
대상 객체는 아두이노 보드가 아니라 삼성 artik 모듈을 사용하여 저전력 블루투스 모듈을 사용하여 통신
특이 사항 디폴트 통신 모듈이 아닌 저전력 모듈을 사용하여 구성한 점

## 블루투스 통신을 위해 안드로이드 앱 구성
1. 안드로이드 블루투스 서비스를 이용하여 어댑터를 구성하고 가능한 목록을 구현
2. 연결 로직을 구현 본인이 배정 받은 파트는 블루트스를 통하여 연결을 확인을 하고 명령어 셋 문자열을 통신하는 것

## 4

1. 연결은 테스트를 해봤으나 좀 더 다양한 통신 방법을 사용하는 것이 유리한 것을 확인 비콘을 이용한 통신 방법이나
NFC 를 이용해서 구현을 했으면 좀 더 좋았을 것이라는 생각


