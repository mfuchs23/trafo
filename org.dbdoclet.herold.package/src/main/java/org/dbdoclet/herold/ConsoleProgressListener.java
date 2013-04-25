package org.dbdoclet.herold;

import org.dbdoclet.progress.ProgressAdapter;
import org.dbdoclet.progress.ProgressEvent;

public class ConsoleProgressListener extends ProgressAdapter {

	private static final int DEFAULT_LINE_WIDTH = 77;
	private int prepareCounter = 0;
	private int actionCounter = 0;
	private final boolean dumb;
	private int lastPercent = 0;
	private ProgressEvent lastEvent;
	private int barWidth;
	private int lineWidth;
	private String lastLine;

	public ConsoleProgressListener(boolean dumb) {
		
		this.dumb = dumb;
		int lineWidth = DEFAULT_LINE_WIDTH;
				
		String value = System.getProperty("console.lineWidth");
		
		if (value != null) {
			try {
				lineWidth = Integer.parseInt(value);
			} catch (Exception e) {
				lineWidth = DEFAULT_LINE_WIDTH;
			}
		}
		
		// print("LineWidth=" + lineWidth + "\n");
		setLineWidth(lineWidth);
	}

	@Override
	public boolean progress(ProgressEvent event) {

		if (lastEvent != null) {

			if (lastEvent.getStage() == ProgressEvent.STAGE_ACTION && lastEvent.getConsider() == true
					&& (event.getStage() != ProgressEvent.STAGE_ACTION || event.getConsider() == false)) {

				printBar(100);
			}
		}

		if (event.getConsider() == false) {
			
			print("\n" + event.getAction() + "\n");
			lastEvent = event;
			return true;
		}

		if (event.getStage() == ProgressEvent.STAGE_PREPARE) {
			stagePrepare(event);
		}

		if (event.getStage() == ProgressEvent.STAGE_ACTION) {

			actionCounter++;

			int percent = 0;
			int max = getProgressMaximum();

			if (max > 0) {
				percent = (100 * actionCounter) / getProgressMaximum();
			}

			if (percent > 100) {
				percent = 100;
			}

			printBar(percent);
		}

		lastEvent = event;
		return true;
	}

	private void stagePrepare(ProgressEvent event) {
		
		if (dumb == false) {
			
			StringBuilder buffer = new StringBuilder(String.valueOf(++prepareCounter));
			buffer.append(' ');
			
			String action = event.getAction();
			
			if (action != null) {
				action = action.replaceAll("\n", " ").trim();
				action = action.replaceAll("\r", " ").trim();
				buffer.append(action);
			}
			
			while (buffer.length() < lineWidth) {
				buffer.append(' ');
			}
			
			if (buffer.length() > lineWidth) {
				buffer = buffer.delete(lineWidth, buffer.length());
			}
			
			print(buffer.toString() + "\r");
		}
	}

	private void printBar(int percent) {
		
		int pos = 0;

		if (percent > 0) {
			pos = (int) (percent * ((float) barWidth / 100));
		}

		StringBuilder buffer = new StringBuilder();

		for (int i = 0; i < pos; i++) {
			buffer.append('#');
		}

		for (int i = pos; i < barWidth; i++) {
			buffer.append(' ');
		}

		if (dumb == true) {

			if (percent != lastPercent) {
				System.out.println(percent + "%");
				lastPercent = percent;
			}

		} else {
			String line = String.format(" %3d%% |%s| 100%%\r", percent,
					buffer.toString());
			print(line);
		}
	}

	@Override
	public void setProgressMaximum(int max) {
		super.setProgressMaximum(max);
		actionCounter = 0;
	}

	public void setLineWidth(int lineWidth) {
		
		if (lineWidth < 15) {
			return;
		}

		this.lineWidth = lineWidth;
		barWidth = lineWidth - 13;
	}

	private void print(String line) {
		
		if (lastLine != null && line.equals(lastLine)) {
			return;
		}
		
		System.out.print(line);
		lastLine = line;
	}
}
