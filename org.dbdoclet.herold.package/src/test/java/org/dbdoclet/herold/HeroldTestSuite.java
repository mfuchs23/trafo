package org.dbdoclet.herold;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ATests.class,
	BrTests.class,
	CommentTests.class,
	DistributionTests.class,
	InfoTests.class,
	MiscTests.class,
	ParameterTests.class,
	PdfToHtmlTests.class,
	PreTests.class,
	SectionTests.class,
	TitleTests.class,
	UlOlTests.class
})
public class HeroldTestSuite {

}
