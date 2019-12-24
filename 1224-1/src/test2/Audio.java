package test2;

public class Audio implements RemoteControl{
	int vol;
	
	@Override
	public void turnOn() {
		System.out.println("오디오 전원을 켭니다.");
		
	}

	@Override
	public void turnOff() {
		System.out.println("오디오 전원을 끕니다.");
		
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
