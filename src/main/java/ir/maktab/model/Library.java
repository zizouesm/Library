package ir.maktab.model;

import ir.maktab.exception.LibraryFullException;
import ir.maktab.exception.MemberNotFoundException;

import java.util.List;

public class Library {

    private Member[] members;
    private final int capacity;
    private IdGenerator idGenerator;

//	private static int index = 0;

    public Library(int capacity, IdGenerator idGenerator) {
        this.capacity = capacity;
        this.members = new Member[capacity];
        this.idGenerator = idGenerator;
    }

    public void register(Member member) throws LibraryFullException {
        if (isFull()) {
            throw new LibraryFullException("Library is full");
        } else if (member.equals("null")) {
            throw new IllegalAccessError("member is null");
        } else if (member.getFirstName().equals("null")) {
            throw new IllegalAccessError("member.firstName is null");
        } else if (member.getLastName().equals("null")) {
            throw new IllegalAccessError("member.lastName is null");
        } else {
            Member[] newMembers = new Member[members.length + 1];
            for (int i = 0; i < members.length; i++) {
                newMembers[i] = members[i];
            }
            newMembers[newMembers.length - 1] = member;
            members = newMembers;
            Generate generate = new Generate();
            member.setId(generate.generate());
        }

//		members[index] = member;
//		index++;
    }

    public Member unregister(int id) throws MemberNotFoundException {
        Member[] newMembers = new Member[members.length - 1];
        int index = 0;
        Member deletedMember = null;
        for (int i = 0; i < members.length; i++) {
            if (members[i].getId() == id) {
                deletedMember = members[i];
            } else {
                newMembers[index] = members[i];
                index++;
            }
        }
        if (deletedMember == null) {
            throw new MemberNotFoundException("No member found with the given id");
        } else {
            members = newMembers;
        }
        return deletedMember;
    }

    public int getMemberCount() {
        return members.length;
    }

    public boolean isFull() {
        return members.length == capacity;
    }

    public Member[] getMembers() {
        return members;
    }

    public int getCapacity() {
        return capacity;
    }

}
