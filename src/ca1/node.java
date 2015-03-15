/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca1;

/**
 *
 * @author Maziar
 */
public class node
{
	public node()
	{

	}

	public node(char A,node badi)
	{
		a = A;
		next = badi;
	}

	public char a;
	public node next;
}
