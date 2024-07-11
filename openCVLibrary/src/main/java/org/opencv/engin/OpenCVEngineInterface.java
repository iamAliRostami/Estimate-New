/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package org.opencv.engin;
/**
* Class provides a Java interface for OpenCV Engine Service. It's synchronous with native OpenCVEngine class.
*/
public interface OpenCVEngineInterface extends android.os.IInterface
{
  /** Default implementation for OpenCVEngineInterface. */
  class Default implements org.opencv.engin.OpenCVEngineInterface
  {
    /**
        * @return Returns service version.
        */
    @Override public int getEngineVersion() {
      return 0;
    }
    /**
        * Finds an installed OpenCV library.
        * @return Returns path to OpenCV native libs or an empty string if OpenCV can not be found.
        */
    @Override public java.lang.String getLibPathByVersion(java.lang.String version) {
      return null;
    }
    /**
        * Tries to install defined version of OpenCV from Google Play Market.
        * @return Returns true if installation was successful or OpenCV package has been already installed.
        */
    @Override public boolean installVersion(java.lang.String version) {
      return false;
    }
    /**
        * Returns list of libraries in loading order, separated by semicolon.
        * @return Returns names of OpenCV libraries, separated by semicolon.
        */
    @Override public java.lang.String getLibraryList(java.lang.String version) {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  abstract class Stub extends android.os.Binder implements org.opencv.engin.OpenCVEngineInterface
  {
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an org.opencv.engin.OpenCVEngineInterface interface,
     * generating a proxy if needed.
     */
    public static org.opencv.engin.OpenCVEngineInterface asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin instanceof OpenCVEngineInterface))) {
        return ((org.opencv.engin.OpenCVEngineInterface)iin);
      }
      return new org.opencv.engin.OpenCVEngineInterface.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
        if (code == INTERFACE_TRANSACTION) {
            reply.writeString(descriptor);
            return true;
        }
      switch (code)
      {
        case TRANSACTION_getEngineVersion:
        {
          data.enforceInterface(descriptor);
          int _result = this.getEngineVersion();
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        case TRANSACTION_getLibPathByVersion:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _result = this.getLibPathByVersion(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_installVersion:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          boolean _result = this.installVersion(_arg0);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getLibraryList:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _result = this.getLibraryList(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements org.opencv.engin.OpenCVEngineInterface
    {
      private final android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
          * @return Returns service version.
          */
      @Override public int getEngineVersion() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getEngineVersion, _data, _reply, 0);
          if (!_status) {
            if (getDefaultImpl() != null) {
              return getDefaultImpl().getEngineVersion();
            }
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
          * Finds an installed OpenCV library.
          * @return Returns path to OpenCV native libs or an empty string if OpenCV can not be found.
          */
      @Override public java.lang.String getLibPathByVersion(java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getLibPathByVersion, _data, _reply, 0);
          if (!_status) {
            if (getDefaultImpl() != null) {
              return getDefaultImpl().getLibPathByVersion(version);
            }
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
          * Tries to install defined version of OpenCV from Google Play Market.
          * @return Returns true if installation was successful or OpenCV package has been already installed.
          */
      @Override public boolean installVersion(java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_installVersion, _data, _reply, 0);
          if (!_status) {
            if (getDefaultImpl() != null) {
              return getDefaultImpl().installVersion(version);
            }
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
          * Returns list of libraries in loading order, separated by semicolon.
          * @return Returns names of OpenCV libraries, separated by semicolon.
          */
      @Override public java.lang.String getLibraryList(java.lang.String version) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(version);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getLibraryList, _data, _reply, 0);
          if (!_status) {
            if (getDefaultImpl() != null) {
              return getDefaultImpl().getLibraryList(version);
            }
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static org.opencv.engin.OpenCVEngineInterface sDefaultImpl;
    }
    static final int TRANSACTION_getEngineVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION);
    static final int TRANSACTION_getLibPathByVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_installVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_getLibraryList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    public static boolean setDefaultImpl(org.opencv.engin.OpenCVEngineInterface impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static org.opencv.engin.OpenCVEngineInterface getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  java.lang.String DESCRIPTOR = "org.opencv.engin.OpenCVEngineInterface";
  /**
      * @return Returns service version.
      */
  int getEngineVersion() throws android.os.RemoteException;
  /**
      * Finds an installed OpenCV library.
      * @return Returns path to OpenCV native libs or an empty string if OpenCV can not be found.
      */
  java.lang.String getLibPathByVersion(java.lang.String version) throws android.os.RemoteException;
  /**
      * Tries to install defined version of OpenCV from Google Play Market.
      * @return Returns true if installation was successful or OpenCV package has been already installed.
      */
  boolean installVersion(java.lang.String version) throws android.os.RemoteException;
  /**
      * Returns list of libraries in loading order, separated by semicolon.
      * @return Returns names of OpenCV libraries, separated by semicolon.
      */
  java.lang.String getLibraryList(java.lang.String version) throws android.os.RemoteException;
}
