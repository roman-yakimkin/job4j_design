package ru.job4j.multithreads;

/**
 * Класс - барьер для запуска потока
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.06.2020
 * @version 1.0
 */
public class MasterSlaveBarrier {
    private Boolean isMaster;
    private Boolean isSlave;

    public MasterSlaveBarrier(Boolean isMaster, Boolean isSlave) {
        this.isMaster = isMaster;
        this.isSlave = isSlave;
    }

    public synchronized void tryMaster() {
        while (isMaster) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void trySlave() {
        while (isSlave) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doneMaster() {
        isMaster = false;
        isSlave = true;
        notifyAll();
    }

    public synchronized void doneSlave() {
        isMaster = true;
        isSlave = false;
        notifyAll();
    }
}