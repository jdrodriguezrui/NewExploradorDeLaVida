/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author julia
 */
public class MusicThread extends Thread {

    @Override
    public void run() {
        
        try {
            URL url = this.getClass().getResource("/resources/this_game.wav");
            File wav = new File(url.toURI());
            playSoundFile(wav);
        } catch (URISyntaxException ex) {
            System.out.println("URI NOT VALID");
        }
        
    }

    public void playSoundFile(File file) {//sacado de http://java.ittoolbox.com/groups/technical-functional/java-l/sound-in-an-application-90681

        try {
            //get an AudioInputStream
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            //get the AudioFormat for the AudioInputStream
            AudioFormat audioformat = ais.getFormat();           
            //INFO DEL ARCHIVO
            /*System.out.println("Format: " + audioformat.toString());
            System.out.println("Encoding: " + audioformat.getEncoding());
            System.out.println("SampleRate:" + audioformat.getSampleRate());
            System.out.println("SampleSizeInBits: " + audioformat.getSampleSizeInBits());
            System.out.println("Channels: " + audioformat.getChannels());
            System.out.println("FrameSize: " + audioformat.getFrameSize());
            System.out.println("FrameRate: " + audioformat.getFrameRate());
            System.out.println("BigEndian: " + audioformat.isBigEndian());*/
            //ULAW format to PCM format conversion
            if ((audioformat.getEncoding() == AudioFormat.Encoding.ULAW)
                    || (audioformat.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newformat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        audioformat.getSampleRate(),
                        audioformat.getSampleSizeInBits() * 2,
                        audioformat.getChannels(),
                        audioformat.getFrameSize() * 2,
                        audioformat.getFrameRate(), true);
                ais = AudioSystem.getAudioInputStream(newformat, ais);
                audioformat = newformat;
            }

            //checking for a supported output line
            DataLine.Info datalineinfo = new DataLine.Info(SourceDataLine.class, audioformat);
            if (!AudioSystem.isLineSupported(datalineinfo)) {
                //System.out.println("Line matching " + datalineinfo + " is not supported.");
            } else {
                //System.out.println("Line matching " + datalineinfo + " is supported.");
                //opening the sound output line
                SourceDataLine sourcedataline = (SourceDataLine) AudioSystem.getLine(datalineinfo);
                sourcedataline.open(audioformat);
                sourcedataline.start();
                //Copy data from the input stream to the output data line
                int framesizeinbytes = audioformat.getFrameSize();
                int bufferlengthinframes = sourcedataline.getBufferSize() / 8;
                int bufferlengthinbytes = bufferlengthinframes * framesizeinbytes;
                byte[] sounddata = new byte[bufferlengthinbytes];
                int numberofbytesread = 0;
                while ((numberofbytesread = ais.read(sounddata)) != -1) {
                    int numberofbytesremaining = numberofbytesread;
                    sourcedataline.write(sounddata, 0, numberofbytesread);
                }
            }

        } catch (IOException e) {
            System.out.println("Wild IOException has appeared!");
        } catch (LineUnavailableException ex) {
            System.out.println("Wild LineUnavailableException appeared!");
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Wild UnsupportedAudioFileException appeared!");
        }
    }
}
