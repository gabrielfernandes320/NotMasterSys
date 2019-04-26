//
// Pacote ao qual a classe pertence
//
package 		lib;

//
// Importa as bibliotecas Java
//
import			java.awt.event.FocusAdapter;
import			java.awt.event.FocusEvent;
import java.math.BigDecimal;

import			javax.swing.JFormattedTextField;
import			javax.swing.JTextField;
import			javax.swing.event.CaretEvent;
import			javax.swing.event.CaretListener;
import			javax.swing.text.AttributeSet;
import			javax.swing.text.BadLocationException;
import			javax.swing.text.PlainDocument;
import			javax.swing.text.SimpleAttributeSet;

/**

@author		Matheus Leandro Ferreira
@version	$Revision: 0.0 $

======================================================================
Arquivo		:	$RCSfile: MasterWindowCadastroModalidade.java,v $
Projeto		:	MasterSys
Criação		:	09/03/2011
Descrição	:	Componente para tratamento de valores monetarios.
======================================================================

@Matheus 2011-18-04
- Criação da classe para valores monetarios.

*/

public class		MasterMoneyField 
	extends		JFormattedTextField 
{
	private static final 	SimpleAttributeSet 
	nullAttribute		=	new SimpleAttributeSet();
	
	/**
	Creates a new instance of JMoneyField
	*/
	public MasterMoneyField() 
	{
		this.setHorizontalAlignment( JTextField.RIGHT );
		this.setDocument(new MoneyFieldDocument());
		this.addFocusListener(new MoneyFieldFocusListener());
		this.setText("0,00");
		this.addCaretListener
		(
			new CaretListener()
			{
				public void caretUpdate(CaretEvent e) 
				{
					if (e.getDot() != getText().length() ) 
					{
						getCaret().setDot(getText().length());
					}
				}
			}
		);
	}
    
	private final class	MoneyFieldFocusListener 
		extends		FocusAdapter
	{
		public 
		void			focusGained(FocusEvent e) 
		{
			selectAll();
		}
	}
    
	private final class	MoneyFieldDocument 
		extends		PlainDocument 
	{
		public 
		void			insertString
					(
						int			offs, 
						String			str, 
						AttributeSet		a
					)
			throws BadLocationException 
		{
			String 
			original		=	getText(0,getLength());
     		
			// Permite apenas digitar até 16 caracteres (9.999.999.999,99)
			if	(
					original.length() 	<	16
				)
			{
				StringBuffer 
				mascarado			=	new StringBuffer();
				
				if	(
						a			!=	nullAttribute
					)
				{
					//limpa o campo
					remove(-1,getLength());
     				
					mascarado.append((original+str).replaceAll("[^0-9]",""));
					
					for	(
							int
							i			=	0
							;
							i			<	mascarado.length()
							; 
							i++
						)
					{
						if	(
								!Character.isDigit(mascarado.charAt(i))
							)
						{
							mascarado.deleteCharAt(i);
						}
					}
					
					Long
					number			=	new Long(mascarado.toString());
         			
	         			mascarado.replace(0, mascarado.length(), number.toString());
	         			
	         			if	(
	         					mascarado.length() 	<	3 
	         				)
	         			{
		         			if	(
		         					mascarado.length() 	==	1 
		         				) 
		         			{
		         				mascarado.insert(0,"0");
		         				mascarado.insert(0,",");
		         				mascarado.insert(0,"0");
		         			}
		         			else if	(
		         					mascarado.length() 	==	2 
		         				) 
		         			{
		         				mascarado.insert(0,",");
		         				mascarado.insert(0,"0");
		         			}
	         			}
	         			else
	         			{
	         				mascarado.insert(mascarado.length()-2,",");
	         			}
	         			
	         			if	(
	         					mascarado.length() 	>	6 
	         				) 
	         			{
	         				mascarado.insert(mascarado.length()-6, '.');
	         				
	         				if	(
	         						mascarado.length() 	>	10 
	         					) 
	         				{
	         					mascarado.insert(mascarado.length()-10, '.');
	         				
	         					if	(
	         							mascarado.length() 	>	14 
	         						)
	         					{
	         						mascarado.insert(mascarado.length()-14, '.');
	         					}
	         				}
	         			}
		         			
		         		super.insertString(0, mascarado.toString(), a);
	     			}
				else
				{
	     				if	(
	     						original.length() 	==	0
	     					)
	     				{
	     					super.insertString(0, "0,00", a);
	     				}
	     			}
			}
		}
    	
	    	@Override
	    	public 
	    	void			remove
	    				(
	    					int			offs,
	    					int			len
	    				) 
	    		throws		BadLocationException 
	    	{
	    		if	(
	    				len 			==	getLength() 
	    			) 
	    		{
	    			super.remove(0, len);
	    			
	    			if	(
	    					offs != -1
	    				)
	    			{
		    			insertString(0, "",nullAttribute);
	    			}
	    		}
	    		else
	    		{
	    			String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs+len);
	    			super.remove(0, getLength());
	    			insertString(0,original,null);
	    		}
	    	}
	}
	
	/**
	Retorna o valor numérico contido no campo.
	*/
	public
    	BigDecimal			getValue
    				(
    					
    				)
    	{
		try
		{
			return	BigDecimal.valueOf(Double.valueOf(getText().trim().replace(".", "").replace(",", ".")));
		}
    		catch	(Exception	ex)
    		{
    			return	null;
    		}
    	}
	
	/**
	Seta um valor numérico no campo.
	@param	an_value
		Valor numérico.
	*/
	public
    	void			setValue
    				(
    					BigDecimal		an_value
    				)
    	{
    		if	(
    				an_value		==	null
    			)
    		{
    			this.setText("0,00");
    		}
    		else
    		{
    			this.setText(String.valueOf(an_value));
    		}
    	}
}
