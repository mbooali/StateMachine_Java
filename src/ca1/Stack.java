/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca1;

/**
 *
 * @author Maziar
 */

public class Stack
{
	public node head = new node('.',null);

	public void push(char value)
	{
		head.next = new node(value,head.next);
	}

	public node pop()
	{
		node ret;
		ret = head.next;
		head.next = head.next.next;
		return ret;
	}

	public node top()
	{
		return head.next;
	}

	public boolean isEmpty()
	{
		if(head.next == null)
			return true;
		else
			return false;
	}

	public void print()
	{
		node temp;
		temp = head.next;
		System.out.println("This is Your Stack contents...!\n");
		while(!temp.equals(null))
		{
			System.out.printf("%c\n", temp.a);
			temp = temp.next;
		}
	}
}
