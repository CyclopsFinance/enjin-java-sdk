package com.enjin.coin.sdk.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.enjin.coin.sdk.util.JsonUtils;
import com.google.gson.Gson;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JsonConfig.class, Class.class, File.class, FileWriter.class , Gson.class, JsonUtils.class})
public class JsonConfigTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FileDoesntExistSuccess() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);
		
		Mockito.when(mockFile.exists()).thenReturn(false);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(false);
		Mockito.when(mockFile.createNewFile()).thenReturn(true);
		Mockito.doNothing().when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
		Mockito.verify(mockFile, Mockito.times(1)).createNewFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FilesExistsSecondTimeAroundSuccess() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);
		
		Mockito.when(mockFile.exists()).thenReturn(false);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doNothing().when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_IOExceptionPerformingWrite() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);

		Mockito.when(mockFile.exists()).thenReturn(false);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doThrow(new IOException("exception")).when(mockFileWriter).write(Mockito.anyString());

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(1)).close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FileExistsLengthIs0Success() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);
		
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(0l);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doNothing().when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_ParentFileExists() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);
		
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(0l);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(mockFile);
		Mockito.when(mockFile.mkdir()).thenReturn(true);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doNothing().when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(2)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FileLengthis0FilesExistsSecondTimeAroundSuccess() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);

		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(0l);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doNothing().when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FileLengthis0IOExceptionPerformingWrite() throws Exception {
        Class configClass = JsonConfig.class;

		File mockFile = PowerMockito.mock(File.class);
		FileWriter mockFileWriter = PowerMockito.mock(FileWriter.class);
		FileWriter spyFileWriter = Mockito.spy(mockFileWriter);
		
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(0l);
		PowerMockito.whenNew(FileWriter.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileWriter);
		Mockito.when(mockFile.getParentFile()).thenReturn(null);
		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.doThrow(new IOException("exception")).when(spyFileWriter).write(Mockito.anyString());
		Mockito.doNothing().when(spyFileWriter).close();

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(2)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileWriter.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
		Mockito.verify(mockFile, Mockito.times(1)).getParentFile();
	    Mockito.verify(mockFileWriter, Mockito.times(1)).write(Mockito.anyString());
	    Mockito.verify(mockFileWriter, Mockito.times(2)).close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_LoadExistingSuccess() throws Exception {
        Class configClass = JsonConfig.class;
        JsonConfig jsonConfig = new JsonConfig();
        
        PowerMockito.mockStatic(JsonUtils.class);

		File mockFile = PowerMockito.mock(File.class);
		FileReader mockFileReader = PowerMockito.mock(FileReader.class);

		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(1l);
		PowerMockito.whenNew(FileReader.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileReader);
		PowerMockito.when(JsonUtils.convertJsonFromFileReaderToObject(Mockito.isA(Gson.class), Mockito.isA(FileReader.class), Mockito.isA(Class.class))).thenReturn(jsonConfig);

		JsonConfig response = JsonConfig.load(mockFile, configClass);
		assertThat(response).isNotNull();
	
		Mockito.verify(mockFile, Mockito.times(1)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileReader.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
	    PowerMockito.verifyStatic(JsonUtils.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLoad_FailedToLoadExistingSuccess() throws Exception {
        Class configClass = JsonConfig.class;
        JsonConfig jsonConfig = null;
        
        PowerMockito.mockStatic(JsonUtils.class);

		File mockFile = PowerMockito.mock(File.class);
		FileReader mockFileReader = PowerMockito.mock(FileReader.class);

		Mockito.when(mockFile.exists()).thenReturn(true);
		Mockito.when(mockFile.length()).thenReturn(1l);
		PowerMockito.whenNew(FileReader.class).withParameterTypes(File.class).withArguments(mockFile).thenReturn(mockFileReader);
		PowerMockito.when(JsonUtils.convertJsonFromFileReaderToObject(Mockito.isA(Gson.class), Mockito.isA(FileReader.class), Mockito.isA(Class.class))).thenReturn(jsonConfig);
		
		try {
			JsonConfig response = JsonConfig.load(mockFile, configClass);
			assertThat(response).isNull();
		} catch (Exception e) {
			assertThat(e).isNotNull();
		}
		Mockito.verify(mockFile, Mockito.times(1)).exists();
		Mockito.verify(mockFile, Mockito.times(1)).length();
	    PowerMockito.verifyNew(FileReader.class, Mockito.times(1)).withArguments(Mockito.isA(File.class));
	    PowerMockito.verifyStatic(JsonUtils.class);
	}

}
