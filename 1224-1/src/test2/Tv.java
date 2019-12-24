package test2;

public class Tv implements RemoteControl {
	int vol;
	
	@Override
	public void turnOn() {
		System.out.println("TV 전원 ON");
		
	}

	@Override
	public void turnOff() {
		System.out.println("TV 전원 OFF");
		
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
	@Override
	public void setMute(boolean mute) {
		if(mute) {
		System.out.println("TV 음소거");
		}
		else {
		System.out.println("TV 음소거 해제");
	}
	}

}
