package com.kanda.graphql.component.fake;

import com.kanda.graphql.datasource.fake.FakeBookDataSource;
import com.kanda.graphql.datasource.fake.FakeHelloDataSource;
import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.Book;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeBookDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = "books")
    public List<Book> booksWrittenBy(@InputArgument(name = "author")
                                   Optional<String> authorInput){
        if(!authorInput.isPresent()){
            return FakeBookDataSource.BOOK_LIST;
        }

        var authorName = authorInput.get();

        return FakeBookDataSource.BOOK_LIST.stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }
}
