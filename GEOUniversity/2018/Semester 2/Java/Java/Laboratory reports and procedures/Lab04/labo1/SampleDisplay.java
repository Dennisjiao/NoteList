package jodt;

class SampleDisplay {
	public void display(Soundable soundable) {
        soundable.playSound();
        soundable.decreaseVolume();
        soundable.stopSound();
        soundable.increaseVolume();
    }

}
