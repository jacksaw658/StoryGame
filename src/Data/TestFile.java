package Data;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class TestFile extends Canvas implements Runnable
{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;


    public TestFile()
    {
        new Window(WIDTH, HEIGHT, "WUBBA LUBBA DUB DUB!", this);
    }
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop ()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {

    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = ((BufferStrategy) bs).getDrawGraphics();

        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        System.out.println("data");
        System.out.println("nice");
        //dsad
        System.out.println("data");
        System.out.println("my mom claps her ass for money");
        new TestFile();

        System.out.println("what");
        System.out.println("wow");
        System.out.println("data");
    }
}
