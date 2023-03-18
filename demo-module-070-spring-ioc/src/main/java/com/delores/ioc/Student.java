package com.delores.ioc;

import java.util.*;

/**
 * @author William
 * @date 3/24/21 10:33 AM
 * @description
 */
public class Student {

    public Student() {
    }

    public Student(String name, Address address, String[] girls, List<String> books, Set<String> games, Map<String, String> cards, String wife, Properties info) {
        this.name = name;
        this.address = address;
        this.girls = girls;
        this.books = books;
        this.games = games;
        this.cards = cards;
        this.wife = wife;
        this.info = info;
    }

    private String name;
    private Address address;
    private String[] girls;
    private List<String> books;
    private Set<String> games;
    private Map<String, String> cards;
    private String wife;
    private Properties info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getGirls() {
        return girls;
    }

    public void setGirls(String[] girls) {
        this.girls = girls;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public Map<String, String> getCards() {
        return cards;
    }

    public void setCards(Map<String, String> cards) {
        this.cards = cards;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", girls=" + Arrays.toString(girls) +
                ", books=" + books +
                ", games=" + games +
                ", cards=" + cards +
                ", wife='" + wife + '\'' +
                ", info=" + info +
                '}';
    }
}
