/*
 * CalendarComboBox.java
 */

package			lib;

import			java.awt.BorderLayout;
import java.awt.Color;
import			java.awt.event.ActionEvent;
import			java.awt.event.ActionListener;
import			java.util.Calendar;
import			java.util.Date;
import			java.util.GregorianCalendar;
import			javax.swing.BorderFactory;
import			javax.swing.JLabel;
import			javax.swing.JPanel;
import			javax.swing.SwingUtilities;
import			javax.swing.Timer;

public class		MasterMonthChooser
	extends		JPanel
{
	private			GregorianCalendar
	gc;
	
	private			Timer
	timer;
	
	private			JLabel
  	dateLabel,
  	nextMonth,
  	nextYear,
  	previousMonth,
  	previousYear;
  	
  	private			JPanel
  	navegatePanel;
	
	private			String[]
	month;
	
	private			int
	timerFlag		=	0;
	
	private static final	int
	TIME_FROZEN		=	3;
  
	/**
	Construtor padrão
	*/
	public			MasterMonthChooser()
	{
		initComponents();
		
		gc			=	new GregorianCalendar();
		
		//
		// Set border
		//
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		setSize(183, 24);
    
		nextYear.addMouseListener
		(
			new java.awt.event.MouseAdapter()
			{
				public void mousePressed(java.awt.event.MouseEvent evt)
				{
					nextYear.setBorder(BorderFactory.createLoweredBevelBorder());
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						initTimer(true, false, true, false);
					}
				}
      
				public void mouseReleased(java.awt.event.MouseEvent evt)
				{
					nextYear.setBorder(BorderFactory.createRaisedBevelBorder());
        
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						terminateTimer();
						if	(
								timerFlag		<=	TIME_FROZEN
							)
						{
							prepare(true, false, true, false);
						}
						
						timerFlag = 0;
					}
					
					ExecuteSomething();
				}
			}
		);
    
		nextMonth.addMouseListener
		(
			new java.awt.event.MouseAdapter()
			{
				public void mousePressed(java.awt.event.MouseEvent evt)
				{
					nextMonth.setBorder(BorderFactory.createLoweredBevelBorder());
					
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						initTimer(false, true, true, false);
					}
				}
	      
				public void mouseReleased(java.awt.event.MouseEvent evt)
				{
					nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());
	        
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						terminateTimer();
						
						if	(
								timerFlag		<=	TIME_FROZEN
							)
						{
							prepare(false, true, true, false);
						}
						
						timerFlag = 0;
					}
					
					ExecuteSomething();
				}
			}
		);
    
		previousMonth.addMouseListener
		(
			new java.awt.event.MouseAdapter()
			{
				public void mousePressed(java.awt.event.MouseEvent evt)
				{
					previousMonth.setBorder(BorderFactory.createLoweredBevelBorder());
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						initTimer(false, true, false, false);
					}
				}
      
				public void mouseReleased(java.awt.event.MouseEvent evt)
				{
					previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());
        
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						terminateTimer();
						
						if	(
								timerFlag		<=	TIME_FROZEN
							)
						{
							prepare(false, true, false, false);
						}
						timerFlag = 0;
					}
					
					ExecuteSomething();
				}
			}
		);
    
		previousYear.addMouseListener
		(
			new java.awt.event.MouseAdapter()
			{
				public void mousePressed(java.awt.event.MouseEvent evt)
				{
					previousYear.setBorder(BorderFactory.createLoweredBevelBorder());
					
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						initTimer(true, false, false, false);
					}
				}
      
				public void mouseReleased(java.awt.event.MouseEvent evt)
				{
					previousYear.setBorder(BorderFactory.createRaisedBevelBorder());
        
					if	(
							SwingUtilities.isLeftMouseButton(evt)
						)
					{
						terminateTimer();
						
						if	(
								timerFlag		<=	TIME_FROZEN
							)
						{
							prepare(true, false, false, false);
						}
						timerFlag = 0;
					}
					
					ExecuteSomething();
				}
			}
		);
    

		month		=	new String[] 
	         		 	{
			    			"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
			    			"Julho", "Agosto", "Setembro","Outubro", "Novembro", "Dezembro"
			    		}
	    			;
		//
		// Mostra na tela
		//
		setDaysAndFill();
	}

	private
	void			initComponents()
	{
		navegatePanel		=	new JPanel();
		previousYear		=	new JLabel();
		previousMonth		=	new JLabel();
		dateLabel		=	new JLabel();
		nextMonth		=	new JLabel();
		nextYear		=	new JLabel();

		setLayout(new BorderLayout());

		navegatePanel.setLayout(null);

		navegatePanel.setPreferredSize(new java.awt.Dimension(22, 22));
		previousYear.setFont(new java.awt.Font("Arial", 0, 9));
		previousYear.setText("<<");
		previousYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		previousYear.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

		navegatePanel.add(previousYear);
		previousYear.setBounds(1, 1, 20, 20);

		previousMonth.setFont(new java.awt.Font("Arial", 0, 9));
		previousMonth.setText("<");
		previousMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		previousMonth.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

		navegatePanel.add(previousMonth);
		previousMonth.setBounds(21, 1, 20, 20);

		dateLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
		dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		dateLabel.setText("Escolha a Data");
		navegatePanel.add(dateLabel);
		dateLabel.setBounds(42, 1, 100, 20);

		nextMonth.setFont(new java.awt.Font("Arial", 0, 9));
		nextMonth.setText(">");
		nextMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nextMonth.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

		navegatePanel.add(nextMonth);
		nextMonth.setBounds(142, 1, 20, 20);

		nextYear.setFont(new java.awt.Font("Arial", 0, 9));
		nextYear.setText(">>");
		nextYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nextYear.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
    
		navegatePanel.add(nextYear);
		nextYear.setBounds(162, 1, 20, 20);

		add(navegatePanel, java.awt.BorderLayout.NORTH);

	}                        
  
	private
	void			initTimer
				(
					final boolean		b1,
					final boolean		b2,
					final boolean		b3,
					final boolean		b4
				)
	{
		timer			=	new Timer
						(
							100,
							new ActionListener()
							{
								public void actionPerformed(ActionEvent evt)
								{
									if	(
											timerFlag		>	TIME_FROZEN
										)
									{
										prepare(b1, b2, b3, b4);
									}
									else
									{
										timerFlag++;
									}
								}
							}
						);

		timer.start();
	}
  
	private
	void			terminateTimer()
	{
		timer.stop();
		timer = null;
	}
  
  	private
  	void			setDaysAndFill()
  	{
  		gc.set(Calendar.DAY_OF_MONTH, 1);
  		String monthText = month[gc.get(Calendar.MONTH)];
  		dateLabel.setText(String.valueOf(monthText + ", " + gc.get(Calendar.YEAR)));
  	}
  
  	public
  	void			prepare
  				(
  					boolean			rollYear,
  					boolean			rollMonth,
  					boolean			up,
  					boolean			instantiateGc
  				)
  	{
  		if	(
  				instantiateGc
  			)
  		{
  			gc			=	new GregorianCalendar();
  		}
  		
  		if	(
  				rollYear
  			)
  		{
  			gc.roll(Calendar.YEAR, up);
  		}

  		if	(
  				rollMonth
  			)
  		{
  			if	(
  					(
  						up
  						&&
  						gc.get(Calendar.MONTH)	==	Calendar.DECEMBER
  					)
  					||
  					(
  						!up
  						&&
  						gc.get(Calendar.MONTH)	==	Calendar.JANUARY
  					)
  				)
  			{
  				gc.roll(Calendar.YEAR, up);
  			}
  			
  			gc.roll(Calendar.MONTH, up);
  		}

  		setDaysAndFill();
  	}
  
  	public
  	Date			getDate()
  	{
  		return	gc.getTime();
  	}
  
  	public
  	void			stopTimer()
  	{
  		if	(
  				timer			!=	null
  			)
  		{
  			timer.stop();
  		}
  	}
  	
  	/**
  	Método para ser sobrescrito pela classe que usará
  	este objeto, afim de executar alguma ação quando
  	a data for alterada.
  	*/
  	public
  	void			ExecuteSomething()
  	{
  		
  	}
}
