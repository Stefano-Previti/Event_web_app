package eventwebapp.utility;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtility {
	private static final String E1="Error1";
	private static final String E2="Error2";
	private static final String E3="Error3";
	private static final String E4="Error4";
	private static final String E5="Error5";
	private static final String E6="Error6";
	private static final String E7="Error7"; 
	private static final String E8="Error8";
	private static final String E9="Error9";
	private static final String E10="Error10";
	private static final String E11="Error11";
	private static final String E12="Error12"; 
	private static final String E13="Error13";
	private static final String E14="Error14";
	private static final String E15="Error15";


	
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
		      throws IOException, ServletException {

		    RequestDispatcher rd = request.getRequestDispatcher(page);
		    System.out.println(page);
		    rd.forward(request, response); 
		  }

		  public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
		      throws IOException, ServletException {
		    response.sendRedirect(page);
		  } 
		  public static void setErrorName(String msg, HttpServletRequest request) {
			    request.setAttribute(E1, msg);
			  }

			  public static String getErrorName(HttpServletRequest request) {
			   String val= (String) request.getAttribute(E1);
			   if (val == null) {
				      return "";
				    } else {
				      return val;
				    }
			  }
			  public static void setErrorSurname(String msg, HttpServletRequest request) {
				    request.setAttribute(E2, msg);
				  }

				  public static String getErrorSurname(HttpServletRequest request) {
					  String val= (String) request.getAttribute(E2);
				   if (val == null) {
					      return "";
					    } else {
					      return val;
					    }

				  }
				  public static void setErrorDOB(String msg, HttpServletRequest request) {
					    request.setAttribute(E3, msg);
					  }

					  public static String getErrorDOB(HttpServletRequest request) {
						  String val= (String) request.getAttribute(E3);
						  if (val == null) {
						      return "";
						    } else {
						      return val;
						    }

					  }
				 public static void setErrorMail(String msg, HttpServletRequest request) {
						    request.setAttribute(E4, msg);
						  }

				public static String getErrorMail(HttpServletRequest request) {
					 String val= (String) request.getAttribute(E4);
					if (val == null) {
					      return "";
					    } else {
					      return val;
					    }
						  }

				 public static void setErrorUsernameExists(String msg, HttpServletRequest request) {
					    request.setAttribute(E5, msg);
					  }

			public static String getErrorUsernameExists(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E5);
				if (val == null) {
				      return "";
				    } else {
				      return val;
				    }

					  }
			public static void setErrorEmailExists(String msg, HttpServletRequest request) {
			    request.setAttribute(E6, msg);
			  }

			public static String getErrorEmailExists(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E6);
				if (val == null) {
				      return "";
				    } else {
				      return val;
				    }
			  

			  }
			public static void setErrorPassword(String msg, HttpServletRequest request) {
			    request.setAttribute(E7, msg);
			  }

			public static String getErrorPassword(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E7);
				if (val == null) {
				      return "";
				    } else {
				      return val;
				    }

			  }
			public static void setErrorConfirmedPassword(String msg, HttpServletRequest request) {
			    request.setAttribute(E8, msg);
			  }

			public static String getErrorConfirmedPassword(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E8);
			    if (val == null) {
			        return "";
			      } else {
			        return val;
			      }


			  }
			public static void setErrorLogin(String msg, HttpServletRequest request) {
			    request.setAttribute(E9, msg);
			  }

			public static String getErrorLogin(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E9);
			    if (val == null) {
			        return "";
			      } else {
			        return val;
			      }
			  }
			public static void setErrorUsernameFormat(String msg, HttpServletRequest request) {
				request.setAttribute(E10, msg);
				}

				public static String getErrorUsernameFormat(HttpServletRequest request) {
				String val= (String) request.getAttribute(E10);
				if (val == null) {
				return "";
				} else {
				return val;
					}
				}
				public static void setErrorTitololExists(String msg, HttpServletRequest request) {
				    request.setAttribute(E11, msg);
				  }

				public static String getErrorTitoloExists(HttpServletRequest request) {
					 String val= (String) request.getAttribute(E11);
					if (val == null) {
					      return "";
					    } else {
					      return val;
					    }
				  

				  }
				public static void setErrorLuogo(String msg, HttpServletRequest request) {
				    request.setAttribute(E12, msg);
				  }

				public static String getErrorLuogo(HttpServletRequest request) {
					 String val= (String) request.getAttribute(E12);
					if (val == null) {
					      return "";
					    } else {
					      return val;
					    }

				  }
				public static void setErrorDataEvento(String msg, HttpServletRequest request) {
				    request.setAttribute(E13, msg);
				  }

				public static String getErrorDataEvento(HttpServletRequest request) {
					 String val= (String) request.getAttribute(E13);
				    if (val == null) {
				        return "";
				      } else {
				        return val;
				      }


				  }
				public static void setErrorOrario(String msg, HttpServletRequest request) {
				    request.setAttribute(E14, msg);
				  }

				public static String getErrorOrario(HttpServletRequest request) {
					 String val= (String) request.getAttribute(E14);
				    if (val == null) {
				        return "";
				      } else {
				        return val;
				      }
				  }
				public static void setErrorPosti(String msg, HttpServletRequest request) {
					request.setAttribute(E15, msg);
					}

					public static String getErrorPosti(HttpServletRequest request) {
					String val= (String) request.getAttribute(E15);
					if (val == null) {
					return "";
					} else {
					return val;
						}
					}
}
