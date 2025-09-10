package ass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SysTime {
	public static void showTime(Label systime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// show at the first second
		String initialTime = LocalDateTime.now().format(formatter);
        systime.setText(initialTime);
        
        // update every second after the time shown
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event2 -> {
			String currentTime = LocalDateTime.now().format(formatter);
			systime.setText(currentTime);
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
}
