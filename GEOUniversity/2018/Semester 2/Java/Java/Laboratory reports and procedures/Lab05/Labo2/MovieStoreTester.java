package Movie;

public class MovieStoreTester {
public static void main(String args[]){
	MovieStore aStore=new MovieStore();
	Movie aMovie=new Movie("白毛女","悲剧片");
			aMovie.addActor("田华");
			aMovie.addActor("李百万");
			aMovie.addActor("陈强");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("党的女儿","教育片");
			aMovie.addActor("田华");
			aMovie.addActor("陈戈");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("红色娘子军","教育片");
			aMovie.addActor("祝希娟");
			aMovie.addActor("王新刚");
			aMovie.addActor("陈强");
			aStore.addMovie(aMovie);
			
			aMovie=new Movie("五朵金花","爱情片");
			aMovie.addActor("陈立坤");
			aMovie.addActor("赵丹");
			aStore.addMovie(aMovie);
			
			
			
			
			
			System.out.println("Here are the movies in: "+aStore);
			aStore.listMovies();
			System.out.println();
			
			System.out.println("删除白毛女");
			aStore.removeMovie("白毛女");
			
			
			System.out.println("\n教育片");
			aStore.listMoviesOfType("教育片");
			System.out.println("爱情片");
			aStore.listMoviesOfType("爱情片");
			
			
}
}
