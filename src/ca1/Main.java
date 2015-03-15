/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Maziar Bouali
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException
    {
		int i,t,k;
		boolean sehhat = true;
		Integer b;
		node check;
		Vector<rule> rules = new Vector<rule>();
		Iterator<rule> it;
		//Vector<rule>
		String pop_values = "",states = "",elements = "",allowed = "",temp = "",first_stack = "",finish_state = "";
		char state_check,stack_check,input_check,state_change,state = 0,start_state;
		FileInputStream fin;
		fin = new FileInputStream("pda.txt");
		int a = 0;
		Stack estak;
		estak = new Stack();
		estak.push('A');
		DataInputStream in = new DataInputStream(fin);
		while(true)
		{
			temp = "";
			temp = in.readLine();
			if(temp == null)
			{
				System.out.println("Reading rules has been finished...!");
				break;
			}
			else
			{
				if(temp.charAt(0) == 'M')
				{
					i = 0;
					while (temp.charAt(i) != '{')
						i++;
					
					i++;
					while (temp.charAt(i) != '}')
					{
						if (temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z')
							states += temp.charAt(i);
						
						i++;
					}
					
					while (temp.charAt(i) != '{')
						i++;
					
					i++;
					
					while (temp.charAt(i) != '}')
					{
						if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z')
							elements += temp.charAt(i);
						
						i++;
					}
					
					while (temp.charAt(i) != '{')
						i++;
					
					i++;
					
					while (temp.charAt(i) != '}')
					{
						if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z')
							allowed += temp.charAt(i);
						
						i++;
					}
					
					while (temp.charAt(i) < 'A' || temp.charAt(i) > 'Z')
						i++;
					start_state = temp.charAt(i);
					
					while (temp.charAt(i) < 'a' || temp.charAt(i) > 'z')
						i++;
					first_stack += temp.charAt(i);
					
					while (temp.charAt(i) < 'A' || temp.charAt(i) > 'Z')
						i++;
					finish_state = "";
					while(temp.charAt(i) != '}')
					{
						if(temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z')
						{
							finish_state += temp.charAt(i);
						}
						i++;
					}
					state = start_state;
					estak.push(first_stack.charAt(0));

				}
				else if(temp.charAt(0) == '$')
				{

					i = 0;
					while (temp.charAt(i) < 'A' || temp.charAt(i) > 'Z')
						i++;
					state_check = temp.charAt(i);

					while ((temp.charAt(i) < 'a' || temp.charAt(i) > 'z') && temp.charAt(i) != '@')
						i++;
					input_check = temp.charAt(i);
					i++;
					while ((temp.charAt(i) < 'a' || temp.charAt(i) > 'z') && temp.charAt(i) != '@')
						i++;
					stack_check = temp.charAt(i);

					while (temp.charAt(i) < 'A' || temp.charAt(i) > 'Z')
						i++;
					state_change = temp.charAt(i);

					while((temp.charAt(i) < 'a' || temp.charAt(i) > 'z') && (temp.charAt(i) != '@') )
						i++;

					pop_values = "";
					while(temp.charAt(i) != ')')
					{
						pop_values += temp.charAt(i);
						i++;
					}
					rule toadd;
					toadd = new rule();
					toadd.input_check = input_check;
					toadd.pop_values = pop_values;
					toadd.stack_check = stack_check;
					toadd.state_change = state_change;
					toadd.state_check = state_check;
					rules.addElement(toadd);
				}
			}
		}

		boolean emal;
		fin.close();
		in.close();
		fin = new FileInputStream("input.txt");
		in = new DataInputStream(fin);

		temp = "";
		temp = in.readLine();
		char x;
		rule timp;
		timp = new rule();


		for(i = 0; i < temp.length(); i++)
		{
			emal = false;
			x = temp.charAt(i);
			for(t = 0; t < rules.size(); t++)
			{

				if (rules.elementAt(t).input_check == '@'
					&& rules.elementAt(t).stack_check == estak.top().a
					&& rules.elementAt(t).state_check == state)
				{
					estak.pop();
					state = rules.elementAt(t).state_change;
					for(k = 0; k < rules.elementAt(t).pop_values.length(); k++)
					{
						estak.push(rules.elementAt(t).pop_values.charAt(k));
					}
					emal = true;
					break;
				}
				else if (rules.elementAt(t).input_check == x
					&& rules.elementAt(t).stack_check == estak.top().a
					&& rules.elementAt(t).state_check == state
					&& rules.elementAt(t).pop_values == "@")
				{
					estak.pop();
					state = rules.elementAt(t).state_change;
					emal = true;
					break;
				}
				else if (rules.elementAt(t).input_check == x
					&& rules.elementAt(t).stack_check == estak.top().a
					&& rules.elementAt(t).state_check == state)
				{
					state = rules.elementAt(t).state_change;
					estak.pop();
					for(k = 0; k < rules.elementAt(t).pop_values.length(); k++)
					{
						estak.push(rules.elementAt(t).pop_values.charAt(k));
					}
					emal = true;
					break;
				}

			}
			if(!emal)		//age raft too if yani emal nashod va vooroodi moshkel dare
			{
				sehhat = false;
				break;
			}

		}
		//khob inja file ro khoondim tamoom shod

		//inja bayad check konim ke age dar haalaate finish bood va dar stack chizi nabood OK! ast
		for(i = 0; i < finish_state.length(); i++)
		{
			if(finish_state.charAt(i) == state)
			{
				sehhat = true;
				if(!estak.isEmpty())
				{
					sehhat = false;
					break;
				}
			}
			else
			{
				sehhat = false;
			}
		}

		FileOutputStream yy;
		yy = new FileOutputStream("ans.txt");
		DataOutputStream myout = new DataOutputStream(yy);


		if(sehhat)
		{
			myout.writeUTF("Accepted!");
		}
		else
		{
			myout.writeUTF("Rejected!");
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////
	}


}

