package Movie;

public class MovieStoreTester {
public static void main(String args[]){
	MovieStore aStore=new MovieStore();
	Movie aMovie=new Movie("��ëŮ","����Ƭ");
			aMovie.addActor("�ﻪ");
			aMovie.addActor("�����");
			aMovie.addActor("��ǿ");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("����Ů��","����Ƭ");
			aMovie.addActor("�ﻪ");
			aMovie.addActor("�¸�");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("��ɫ���Ӿ�","����Ƭ");
			aMovie.addActor("ףϣ��");
			aMovie.addActor("���¸�");
			aMovie.addActor("��ǿ");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("����","����Ƭ");
			aMovie.addActor("������");
			aMovie.addActor("�Ե�");
			aStore.addMovie(aMovie);
			
			
			
			
			
			System.out.println("Here are the movies in: "+aStore);
			aStore.listMovies();
			System.out.println();
			
			System.out.println("ɾ����ëŮ");
			aStore.removeMovie("��ëŮ");
			
			
			System.out.println("\n����Ƭ");
			aStore.listMoviesOfType("����Ƭ");
			System.out.println("����Ƭ");
			aStore.listMoviesOfType("����Ƭ");
			
			
}
}
