package test2;

public class SmartTv implements InternetSearch,RemoteControl{ //상속의 경우는 2가지를 한번에 하지 못하지만 인터페이스의 경우 가능하다.

	int vol;
	
	@Override
	public void search(String url) {
		System.out.println("검색 중");
	}

	@Override
	public void turnOn() {
		System.out.println("Smart TV 전원 ON");
		
	}

	@Override
	public void turnOff() {
		System.out.println("Smart TV 전원 OFF");
		
	}

	@Override
	public void setVol(int vol) {
		if(vol>RemoteControl.MAX_VOL) {
			this.vol=RemoteControl.MAX_VOL;
			System.out.println("현재 음량 :"+ RemoteControl.MAX_VOL);
		}
		else if(vol<RemoteControl.MIN_VOL) {
			this.vol=RemoteControl.MIN_VOL;
			System.out.println("현재 음량 :"+ RemoteControl.MIN_VOL);
		}
		else {
			this.vol=vol;
			System.out.println("현재 음량 :"+ vol);
		}
		
	}
		
	}

